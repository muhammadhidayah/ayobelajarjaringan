package com.muhammadhidayah.apps.model;

/**
 * Created by sontoloyo on 5/29/17.
 */

public class Results {

    public static String getResultComment(int numCorrect, int numRounds,
                                          int diff) {
        String comment = "";
        int percentage = calculatePercentage(numCorrect, numRounds);
        switch (diff) {

            default:
                if (percentage > 90) {
                    comment = "Menakjubkan!";
                } else if (percentage >= 80) {
                    comment = "Bagus! Pertahankan";
                } else if (percentage >= 60) {
                    comment = "Lumayan. Tingkatkan lagi belajarnya.";
                } else if (percentage >= 40) {
                    comment = "Masih belum cukup. Belajar lagi yang rajin.";
                } else {
                    comment = "Kamu gagal. Belajar lagi yang rajin";
                }
        }
        return comment;
    }

    private static int calculatePercentage(int numCorrect, int numRounds) {
        double frac = (double) numCorrect / (double) numRounds;
        int percentage = (int) (frac * 100);
        return percentage;
    }

}
