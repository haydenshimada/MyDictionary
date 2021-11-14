package com.example.dictionary.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQL {
    private final Connection connect;
    private PreparedStatement pst = null;
    private final List<String> suggestWord = new ArrayList<>();

    public SQL() throws SQLException {
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
        return res;
    }

    /** trả về table được gợi ý khi nhập chuỗi. */
    public ResultSet searchSuggest(String suggest) throws SQLException {
        final String selectData = "select * from tbl_edict where word like " + "'" + suggest + "%'" + "order by word asc ";
        pst = connect.prepareStatement(selectData);
        // pst.setString(1, suggest);
        ResultSet rs = pst.executeQuery();
        return rs;
    }

    /** đẩy cột word vào list suggestWord và trả về list. */
    public List<String> pushToSuggestList(String suggest) throws SQLException {
        ResultSet rs = searchSuggest(suggest);
        while (rs.next()) {
            suggestWord.add(rs.getString(2));
        }
        return suggestWord;
    }

    /** trả về detail của từ. */
    public String searchWord(String word) throws  SQLException {
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
    public void insertNewWord(final String word, final String wordDetail) throws SQLException {
        final String insertData = "insert into tbl_edict (word, detail) values (?, ?)";
        pst = connect.prepareStatement(insertData);
        pst.setString(1, word);
        pst.setString(2, wordDetail);
        pst.executeUpdate();
    }

    /** xóa từ khỏi table. */
    public void deleteWord(final String word) throws SQLException {
        final String deleteData = "delete from tbl_edict where word = ?";
        pst = connect.prepareStatement(deleteData);
        pst.setString(1, word);
        pst.executeUpdate();
    }

    /** cập nhật detail của từ. */
    public void updateWordDetail(final String word, final String detail) throws SQLException {
        final String updateData = "update tbl_edict set detail = ? where word = ?";
        pst = connect.prepareStatement(updateData);
        pst.setString(1, detail);
        pst.setString(2, word);
        pst.executeUpdate();
    }

    /** kiểm tra xem một từ có ở trong bảng không. */
    public boolean contain(final String word) throws SQLException {
        boolean ok = false;
        final String findWord = "select word from tbl_edict where word = '" + word + "'";
        pst = connect.prepareStatement(findWord);
        ResultSet rs = pst.executeQuery();
        ok = rs.next();
        return ok;
    }
}
