package com.example.dictionary.API;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryAPI {
    private static final List<String> history = new ArrayList<>(); // Lưu history vào một List kiểu String.

    private static final String url = "src/main/resources/com/example/dictionary/History";// Điền đường dẫn đến file history.txt

    public static void getHistoryData() {

        history.clear();

        /* Đọc dữ liệu từ url bằng BufferedReader. Sử dụng try catch để tránh lỗi đọc dữ liệu */
        try (FileInputStream fileInputStream = new FileInputStream(url);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                final String similarString = line;
                history.removeIf(word -> (word.equals(similarString))); // Loại bỏ những từ rỗng

                history.add(line);//Thêm từ mới
                line = bufferedReader.readLine();
            }
        }
        /* Xử lý exception khi có lỗi liên quan đến file input/output. */ catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertWordToHistory(String word) {

        try (FileWriter fileWriter = new FileWriter(url, true); // lưu từ cuối lên
             BufferedWriter bufferedwriter = new BufferedWriter(fileWriter)) {
            bufferedwriter.write(word); //Ghi vào chuỗi word
            bufferedwriter.newLine(); //Xuống dòng
            bufferedwriter.flush(); // Chi thẳng vào fileWrite.
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Không lặp từ trong file text
        getHistoryData(); //Lưu lại từ file và list

        //Xử lý từ lặp
        for (String element : history) {
            //Nếu như từ đấy chưa có trong list thì thêm vào
            if (!history.contains(element)) {
                history.add(element);
            }
        }

        clearTheFile(); //Xoá dữ liệu trong file

        // Đọc lại từ List History vào file. */
        try (FileWriter fileWriter = new FileWriter(url, true); // lưu từ cuối lên
             BufferedWriter bufferedwriter = new BufferedWriter(fileWriter)) {
            for (String s : history) {
                bufferedwriter.write(s); //Ghi vào file
                bufferedwriter.newLine(); //Xuống dòng
                bufferedwriter.flush(); // Chỉ thẳng vào fileWrite.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Hàm trả về List lịch sử mới.
     */
    public List<String> getHistorySet() {
        ArrayList<String> newHistory = new ArrayList<>(history.size());
        for (int i = history.size() - 1; i >= 0; i--) {
            newHistory.add(history.get(i));
        }
        return newHistory;
    }

    /**
     * Xoá 1 từ trong file.
     */
    public static void deleteWord(String word) {
        history.remove(word); // Xoá 1 từ trong List history

        clearTheFile();//Xoá dữ liệu trong file

        // Đọc lại từ List History vào file. */
        try (FileWriter fileWriter = new FileWriter(url, true); // lưu từ cuối lên
             BufferedWriter bufferedwriter = new BufferedWriter(fileWriter)) {
            for (String s : history) {
                bufferedwriter.write(s); //Ghi vào file
                bufferedwriter.newLine(); //Xuống dòng
                bufferedwriter.flush(); // Chỉ thẳng vào fileWrite.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Xoá toàn bộ từ trong file lịch sử.
     */
    public static void clearTheFile() {
        // FileWriter fwOb
        try (FileWriter fwOb = new FileWriter(url, false);
             PrintWriter pwOb = new PrintWriter(fwOb, false)) {
            pwOb.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    public static void main(String[] args) {
        HistoryAPI test1 = new HistoryAPI();
        test1.getHistoryData();
        List<String> test = test1.getHistorySet();
        for (int i = 0; i < test.size(); i++) {
            System.out.println(test.get(i));
        }
  }
     */
}
