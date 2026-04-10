package com.algorithms.app.io.booking.com;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Leetcode2512_TopKStudents {
    public static void main(String[] args) {
        var solution = new Leetcode2512_TopKStudents();
        System.out.println(
                solution.topStudents(
                        new String[] {"smart","brilliant","studious"},
                        new String[] {"not"},
                        new String[] {"this student is studious","the student is smart"},
                        new int[] {1,2},
                        2
                )
        );
    }
    static class Feedback {
        int score;
        int studentId;

        Feedback(int score, int studentId) {
            this.score = score;
            this.studentId = studentId;
        }
    }

    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        var response = new ArrayList<Integer>(k);
        var pq = new PriorityQueue<Feedback>((a, b) -> {
                    if (a.score == b.score)
                        return Integer.compare(a.studentId, b.studentId);
                    else
                        return Integer.compare(b.score, a.score);
                }
        );

        for(int i = 0; i < report.length; i++) {
            int score = 0;
            score += calculatePositiveFeedback(report[i], positive_feedback);
            score -= calculateNegativeFeedback(report[i], negative_feedback);
            pq.offer(new Feedback(score, student_id[i]));
        }

        while(k > 0 && !pq.isEmpty()) {
            response.add(pq.poll().studentId);
            k--;
        }

        return response;
    }

    private int calculateNegativeFeedback(String report, String[] negativeFeedback) {
        int score = 0;
        for(var negative: negativeFeedback) {
            if(report.contains(negative)) {
                score += 1;
            }
        }
        return score;
    }

    private int calculatePositiveFeedback(String report, String[] positiveFeedback) {
        int score = 0;
        for(var positive: positiveFeedback) {
            if(report.contains(positive)) {
                score += 3;
            }
        }
        return score;
    }
}
