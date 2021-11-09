package com.example.dictionary.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQL {
    private static Connection connect = null;
    private static PreparedStatement pst = null;
    private static List<Word> dictionary = new ArrayList<>();
    private static List<String> suggestWord = new ArrayList<>();

    public SQL() throws SQLException, ClassNotFoundException {
        connect = MySQLConnUtils.getMySQlConnection();
    }

    public static String fixInput(String s) {
        String res = s;
        res = res.replace("<C>", "");
        res = res.replace("<F>", "");
        res = res.replace("<I>", "");
        res = res.replace("<N>", "");
        res = res.replace("<Q>", "");
        res = res.replace("</C>", "");
        res = res.replace("</F>", "");
        res = res.replace("</I>", "");
        res = res.replace("</N>", "");
        res = res.replace("</Q>", "");
        res = res.replace("<br />", "\n");
        res = res.replace("=", "\t");
        /**
         res = res.replace("* ", "");
         res = res.replace("- ", "");
         res = res.replace("\t", "");
         */
        return res;
    }

    /** trả về cả table. */
    public static ResultSet selectAllData() throws SQLException {
        final String selectAllData = "select * from tbl_edict";
        pst = connect.prepareStatement(selectAllData);
        ResultSet rs = pst.executeQuery();
        return rs;
    }

    /** đẩy table vào list dictionary và trả về list. */
    public static List<Word> pushAllToList() throws SQLException {
        ResultSet rs = selectAllData();
        while (rs.next()) {
            dictionary.add(new Word(rs.getString(2), fixInput(rs.getString(3))));
        }
        return dictionary;
    }

    /** trả về table được gợi ý khi nhập chuỗi. */
    public static ResultSet searchSuggest(String suggest) throws SQLException {
        final String selectData = "select * from tbl_edict where word like " + "'" + suggest + "%'";
        pst = connect.prepareStatement(selectData);
        // pst.setString(1, suggest);
        ResultSet rs = pst.executeQuery();
        return rs;
    }

    /** đẩy cột word vào list suggestWord và trả về list. */
    public static List<String> pushToSuggestList(String suggest) throws SQLException {
        ResultSet rs = searchSuggest(suggest);
        while (rs.next()) {
            suggestWord.add(rs.getString(2));
        }
        return suggestWord;
    }

    /** trả về detail của từ. */
    public static String searchWord(String word) throws  SQLException {
        final String selectWord = "select detail from tbl_edict where word = ?";
        pst = connect.prepareStatement(selectWord);
        pst.setString(1, word);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {
            return "";
        } else {
            return fixInput(rs.getString(1));
        }
    }

    /** thêm từ mới vào table. */
    public static void insertNewWord(final String word, final String wordDetail) throws SQLException {
        final String insertData = "insert into tbl_edict (word, detail) values (?, ?)";
        pst = connect.prepareStatement(insertData);
        pst.setString(1, word);
        pst.setString(2, wordDetail);
        pst.executeUpdate();
    }

    /** xóa từ khỏi table. */
    public static void deleteWord(final String word) throws SQLException {
        final String deleteData = "delete from tbl_edict where word = ?";
        pst = connect.prepareStatement(deleteData);
        pst.setString(1, word);
        pst.executeUpdate();
    }

    /** cập nhật detail của từ. */
    public static void updateWordDetail(final String word, final String detail) throws SQLException {
        final String updateData = "update tbl_edict set detail = ? where word = ?";
        pst = connect.prepareStatement(updateData);
        pst.setString(1, detail);
        pst.setString(2, word);
        pst.executeUpdate();
    }
}
