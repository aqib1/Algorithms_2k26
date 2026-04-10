package com.algorithms.app.io.booking.com.hackkerrankTest;

import java.util.*;
import java.util.regex.Pattern;

public class AwardTopKHotels {
    private static final Pattern NON_ALPHANUMERIC = Pattern.compile("[^a-z0-9 ]");
    private static final String WHITE_SPACE_REGEX = "\\s+";

    static class HotelScore {
        int id;
        int score;

        HotelScore(int id, int score) {
            this.id = id;
            this.score = score;
        }
    }

    public static List<Integer> awardTopKHotels(String positiveKeywords, String negativeKeywords, List<Integer> hotelIds, List<String> reviews, int k) {
        var positiveTokens = new HashSet<>(Arrays.asList(positiveKeywords.toLowerCase().split(" ")));
        var negativeTokens = new HashSet<>(Arrays.asList(negativeKeywords.toLowerCase().split(" ")));
        var heap = getCustomiseHeapForHotelScoring(k);
        var scoreMap = generateScoreMap(reviews, hotelIds, positiveTokens, negativeTokens);

        for (var entry : scoreMap.entrySet()) {
            heap.offer(new HotelScore(entry.getKey(), entry.getValue()));
        }

        var result = new ArrayList<Integer>();
        while (k > 0 && !heap.isEmpty()) {
            result.add(heap.poll().id);
            k--;
        }

        return result;
    }

    private static Map<Integer, Integer> generateScoreMap(List<String> reviews, List<Integer> hotelIds, HashSet<String> positiveTokens, HashSet<String> negativeTokens) {
        var scoreMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < reviews.size(); i++) {
            var review = NON_ALPHANUMERIC.matcher(reviews.get(i).toLowerCase()).replaceAll(" ");
            var words = review.split(WHITE_SPACE_REGEX);
            var score = 0;

            for (var word : words) {
                if (word.isBlank()) continue;
                if (positiveTokens.contains(word)) score += 3;
                if (negativeTokens.contains(word)) score -= 1;
            }
            scoreMap.put(hotelIds.get(i), scoreMap.getOrDefault(hotelIds.get(i), 0) + score);
        }

        return scoreMap;
    }

    private static Queue<HotelScore> getCustomiseHeapForHotelScoring(int k) {
        return new PriorityQueue<>(k, (a, b) -> {
            if (a.score != b.score)
                return Integer.compare(b.score, a.score); // higher score first if score is not equals
            return Integer.compare(a.id, b.id); // if score equals, then ascending sort by id
        });
    }


}
