package com.contest.rest.service;

import java.util.ArrayList;
import java.util.List;

import com.contest.rest.domain.dto.HotBorrow_infoDTO;
import com.contest.rest.util.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

@Service
public class WebScrapingServiceImpl implements WebScrapingService {

    @Override
    public List<HotBorrow_infoDTO> getHotBorrow() {
        WebDriver driver = WebDriverUtil.getChromeDriver();
        List<HotBorrow_infoDTO> hotBorrowList = new ArrayList<>();

        try {
            driver.get("https://www.data4library.kr/hotTrL");

            // 테이블의 행을 선택
            List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));

            // 상위 5개만 추출
            int maxItems = Math.min(rows.size(), 5); // 최대 5개 또는 행의 개수만큼

            for (int i = 0; i < maxItems; i++) {
                WebElement row = rows.get(i);
                List<WebElement> cells = row.findElements(By.tagName("td"));
                HotBorrow_infoDTO hotBorrowInfo = new HotBorrow_infoDTO();

                // 데이터 매핑
                if (cells.size() >= 6) { // 안전하게 6개 이상의 셀이 있는지 확인
                    hotBorrowInfo.setHb_IBSN(cells.get(5).getText()); // IBSN
                    hotBorrowInfo.setHb_num(Integer.parseInt(cells.get(0).getText())); // 번호
                    hotBorrowInfo.setIncrease(Integer.parseInt(cells.get(1).getText())); // 상승폭
                    hotBorrowInfo.setThisWeek_rank(Integer.parseInt(cells.get(2).getText())); // 이번 주 순위
                    hotBorrowInfo.setLestWeek_rank(Integer.parseInt(cells.get(3).getText())); // 전주 순위

                    // 이미지 URL 추출
                    WebElement imgElement = cells.get(4).findElement(By.tagName("img"));
                    if (imgElement != null) {
                        String imgUrl = imgElement.getAttribute("src");
                        hotBorrowInfo.setThumbnail_url(imgUrl); // 이미지 URL
                    }

                    // 서명 및 출판 정보 추출
                    String titleInfo = cells.get(4).getText();
                    String[] titleInfoParts = titleInfo.split("\n", 2);

                    if (titleInfoParts.length >= 2) {
                        hotBorrowInfo.setTitle(titleInfoParts[0]); // 서명
                        hotBorrowInfo.setInfo(titleInfoParts[1]); // 저자 | 출판사 | 출판년도
                    } else if (titleInfoParts.length == 1) {
                        hotBorrowInfo.setTitle(titleInfoParts[0]); // 서명
                        hotBorrowInfo.setInfo(""); // 추가 정보가 없을 경우 빈 문자열 설정
                    } else {
                        hotBorrowInfo.setTitle(""); // 서명이 없는 경우 빈 문자열 설정
                        hotBorrowInfo.setInfo(""); // 추가 정보가 없는 경우 빈 문자열 설정
                    }
                }

                hotBorrowList.add(hotBorrowInfo);
            }

        } finally {
            WebDriverUtil.quit(driver); // 작업이 끝나면 WebDriver 종료
        }

        return hotBorrowList;
    }
}
