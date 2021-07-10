package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVo;

public class GuestbookDao {
	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver"; // 드라이버
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // IP주소와 포트번호
	private String id = "webdb"; // SQL 계정 이름
	private String pw = "webdb"; // SQL 계정 비밀번호

	// DB 연결
	private void getConnection() {
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원 정리
	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 방명록 리스트
	public GuestbookVo guestNoPw(int num) {

		GuestbookVo guestbookVo = null;

		// 2번, 4번 메소드
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String select = "";
			select += " SELECT ";
			select += "     no, ";
			select += "     password ";
			select += " FROM ";
			select += "     guestbook ";
			select += " WHERE ";
			select += "     no = ? ";

			pstmt = conn.prepareStatement(select);
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String password = rs.getString("password");

				guestbookVo = new GuestbookVo(no, password);
			}

			// 4.결과처리

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();

		return guestbookVo;
	}

	// 방명록 리스트
	public List<GuestbookVo> getGuestbookList() {

		// DB 값을 가져와서 List로 저장
		List<GuestbookVo> guestList = new ArrayList<GuestbookVo>();

		// 2번, 4번 메소드
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String select = "";
			select += " SELECT ";
			select += "     no, ";
			select += "     name, ";
			select += "     password, ";
			select += "     content, ";
			select += "     reg_date ";
			select += " FROM ";
			select += "     guestbook ";
			select += " ORDER BY ";
			select += "     no ASC ";

			pstmt = conn.prepareStatement(select);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String reg_date = rs.getString("reg_date");

				GuestbookVo guestbookVo = new GuestbookVo(no, name, password, content, reg_date);
				guestList.add(guestbookVo);
			}

			// 4.결과처리

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();
		return guestList;
	}

	// 방명록 등록
	public void insert(GuestbookVo guestbookVo) {

		// 2번, 4번 메소드
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String insert = "";
			insert += " INSERT INTO guestbook VALUES ( ";
			insert += "     seq_guestbook_no.NEXTVAL, ";
			insert += "     ?, ";
			insert += "     ?, ";
			insert += "     ?, ";
			insert += "     sysdate ";
			insert += " ) ";

			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, guestbookVo.getName());
			pstmt.setString(2, guestbookVo.getPassword());
			pstmt.setString(3, guestbookVo.getContent());

			pstmt.executeUpdate();

			// 4.결과처리

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();
	}

	// 방명록 삭제
	public void delete(int no, String pw) {

		// 2번, 4번 메소드
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String delete = "";
			delete += " DELETE FROM guestbook ";
			delete += " WHERE ";
			delete += "         password = ? ";
			delete += "     AND no = ? ";

			pstmt = conn.prepareStatement(delete);
			pstmt.setString(1, pw);
			pstmt.setInt(2, no);

			pstmt.executeUpdate();

			// 4.결과처리

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5번 메소드
		this.close();
	}
}
