package org.example._3_memorization.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class P_2304 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<Integer, Integer> positionAndHeightMap = new HashMap<>();

        int barCount = Integer.parseInt(br.readLine());

        for (int i =1; i<= barCount; i++) {
            List<Integer> positionAndHeight = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            positionAndHeightMap.put(positionAndHeight.get(0), positionAndHeight.get(1));
        }

        List<Integer> positions = new ArrayList<>(positionAndHeightMap.keySet()).stream().sorted().collect(Collectors.toList());

        int highestBarPosition = 0;
        int highestBarHeight = 0;

        for (Integer position : positions) {
            int height = positionAndHeightMap.get(position);
            if (height > highestBarHeight) {
                highestBarPosition = position;
                highestBarHeight = height;
            }
        }

        List<Integer> leftBarPositions = new ArrayList<>();
        List<Integer> rightBarPositions = new ArrayList<>(); // 역순

        for (Integer position : positions) {
            if (position < highestBarPosition) {
                leftBarPositions.add(position);
            } else if (position > highestBarPosition){
                rightBarPositions.add(position);
            }
        }

//        rightBarPositions.sort(Comparator.reverseOrder());
        Collections.reverse(rightBarPositions);



        int extent = 0;

        if (!leftBarPositions.isEmpty()) {
            int leftMaxHeight = positionAndHeightMap.get(leftBarPositions.get(0));
            int leftMaxPosition = leftBarPositions.get(0);

            for (int index = 1; index< leftBarPositions.size(); index++) {
                int barHeight = positionAndHeightMap.get(leftBarPositions.get(index));
                int barPosition = leftBarPositions.get(index);

                if (barHeight > leftMaxHeight) {
                    extent += leftMaxHeight * (barPosition - leftMaxPosition);
                    leftMaxHeight = barHeight;
                    leftMaxPosition = barPosition;
                }
            }

            extent += leftMaxHeight * (highestBarPosition - leftMaxPosition);
        }

        if (!rightBarPositions.isEmpty()) {
            int rightMaxHeight = positionAndHeightMap.get(rightBarPositions.get(0));
            int rightMaxPosition = rightBarPositions.get(0);

            for (int index = 1; index< rightBarPositions.size(); index++) {
                int barHeight = positionAndHeightMap.get(rightBarPositions.get(index));
                int barPosition = rightBarPositions.get(index);

                if (barHeight > rightMaxHeight) {
                    extent += rightMaxHeight * (rightMaxPosition-barPosition);
                    rightMaxHeight = barHeight;
                    rightMaxPosition = barPosition;
                }
            }

            extent += rightMaxHeight * (rightMaxPosition- (highestBarPosition));
        }

        extent += highestBarHeight;


        System.out.printf(String.valueOf(extent));
    }
}

