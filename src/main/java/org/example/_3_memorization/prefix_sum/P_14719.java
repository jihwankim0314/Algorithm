package org.example._3_memorization.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class P_14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> widthAndHeight = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());


        int height = widthAndHeight.get(0);
        int width = widthAndHeight.get(1);


        List<Integer> barList = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        int maxHeight = 0;
        int maxHeightBarIndex = 0;
        for (int i = 0; i< barList.size(); i++) {
            if (barList.get(i) > maxHeight) {
                maxHeight = barList.get(i);
                maxHeightBarIndex = i;
            }
        }

        int extent = 0;

        int leftMaxHeight = barList.get(0);
        int leftMaxBarIndex = 0;
        for (int index =1; index< maxHeightBarIndex; index++) {
            int barHeight = barList.get(index);

            if (barHeight > leftMaxHeight) {
                extent += leftMaxHeight * (index - leftMaxBarIndex);
                leftMaxHeight = barHeight;
                leftMaxBarIndex = index;
            }
        }
        extent += leftMaxHeight * (maxHeightBarIndex - leftMaxBarIndex);


        int rightMaxHeight = barList.get(barList.size()-1);
        int rightMaxIndex = barList.size()-1;

        for (int index = barList.size()-2; index> maxHeightBarIndex; index--) {
            int barHeight = barList.get(index);

            if (barHeight > rightMaxHeight) {
                extent += rightMaxHeight * (rightMaxIndex-index);
                rightMaxHeight = barHeight;
                rightMaxIndex = index;
            }
        }
        extent += rightMaxHeight * (rightMaxIndex- (maxHeightBarIndex));


        extent += maxHeight;

        for (Integer bar : barList) {
            extent -= bar;
        }

        System.out.printf(String.valueOf(Math.max(extent, 0)));
    }
}
