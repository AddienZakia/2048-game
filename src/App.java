import java.util.Random;
import java.util.Scanner;

public class App {
    static int[][] data = {
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 }
    };

    static void printArr(int[][] arr) {
        for (int[] x : arr) {
            for (int c : x) {
                System.out.printf("%5d", c);
            }
            System.out.println();
        }
    }

    static void createRandomPosition(int[][] arr) {
        Random rand = new Random();
        int randomX = 0, randomY = 0;

        int[] powIndex2 = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048 };
        randomX = rand.nextInt(4);
        randomY = rand.nextInt(4);

        boolean retry = false;

        for (int c : powIndex2) {
            if (arr[randomY][randomX] == c) {
                retry = true;
            }
        }

        if (retry) {
            createRandomPosition(arr);
        } else {
            data[randomY][randomX] = 2;
        }

    }

    static void checkSame(int[][] arr, String keys, boolean isRun) {
        if (keys.charAt(0) == 'd' || keys.charAt(0) == 'a') {
            for (int i = 0; i < arr.length; i++) {
                for (int j = arr.length - 1; j > 0; j--) {
                    if (keys.charAt(0) == 'd' && arr[i][j] != 0 && arr[i][j] == arr[i][j - 1]) {
                        arr[i][j] += arr[i][j];
                        arr[i][j - 1] = 0;

                        controlller(arr, keys, true);
                    }
                }

                for (int j = 0; j < arr.length - 1; j++) {
                    if (keys.charAt(0) == 'a' && arr[i][j] != 0 && arr[i][j] == arr[i][j + 1]) {
                        arr[i][j + 1] += arr[i][j + 1];
                        arr[i][j] = 0;

                        controlller(arr, keys, true);
                    }
                }
            }
        } else if (keys.charAt(0) == 'w' || keys.charAt(0) == 's') {
            for (int j = 0; j < arr.length; j++) {
                for (int i = arr.length - 1; i > 0; i--) {
                    if (keys.charAt(0) == 's' && arr[i][j] != 0 && arr[i][j] == arr[i - 1][j]) {
                        arr[i][j] += arr[i][j];
                        arr[i - 1][j] = 0;

                        controlller(arr, keys, true);
                    }
                }

                for (int i = 0; i < arr.length - 1; i++) {
                    if (keys.charAt(0) == 'w' && arr[i][j] != 0 && arr[i][j] == arr[i + 1][j]) {
                        arr[i][j] += arr[i][j];
                        arr[i + 1][j] = 0;

                        controlller(arr, keys, true);
                    }
                }
            }
        }
    }

    static void controlller(int[][] arr, String keys, boolean isRun) {
        if (keys.charAt(0) == 'd' || keys.charAt(0) == 'a') {
            for (int i = 0; i < arr.length; i++) {

                int totalNum = 0;
                int[] rowArr = new int[arr.length];
                for (int j = 0; j < arr.length; j++) {
                    if (arr[i][j] != 0) {
                        rowArr[totalNum] = arr[i][j];
                        arr[i][j] = 0;
                        totalNum++;
                    }
                }

                int[] getValue = new int[totalNum];
                for (int k = 0; k < totalNum; k++)
                    getValue[k] = rowArr[k];

                if (keys.charAt(0) == 'd') {
                    int index = 0;
                    for (int j = 4 - totalNum; j <= 3; j++) {
                        arr[i][j] = getValue[index];
                        index++;
                    }

                } else if (keys.charAt(0) == 'a') {
                    int index = 0;
                    for (int j = 0; j < totalNum; j++) {
                        arr[i][j] = getValue[index];
                        index++;
                    }
                }
            }
        } else if (keys.charAt(0) == 'w' || keys.charAt(0) == 's') {
            for (int i = 0; i < arr.length; i++) {
                int totalNum = 0;
                int[] rowArr = new int[arr.length];
                for (int j = 0; j < arr.length; j++) {
                    if (arr[j][i] != 0) {
                        rowArr[totalNum] = arr[j][i];
                        arr[j][i] = 0;
                        totalNum++;
                    }
                }

                int[] getValue = new int[totalNum];
                for (int k = 0; k < totalNum; k++)
                    getValue[k] = rowArr[k];

                if (keys.charAt(0) == 's') {
                    int index = 0;
                    for (int j = 4 - totalNum; j <= 3; j++) {
                        arr[j][i] = getValue[index];
                        index++;
                    }
                } else if (keys.charAt(0) == 'w') {
                    int index = 0;
                    for (int j = 0; j < totalNum; j++) {
                        arr[j][i] = getValue[index];
                        index++;
                    }
                }
            }

        }

        if (!isRun)
            checkSame(arr, keys, false);
    }

    public static void main(String[] args) {
        createRandomPosition(data); // initialiazed

        Scanner input = new Scanner(System.in);

        while (true) {
            String keys;

            printArr(data);
            System.out.print("Arrow keys = ");
            keys = input.nextLine();

            controlller(data, keys.toLowerCase(), false);
            createRandomPosition(data);
        }
    }
}
