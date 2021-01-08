package com.replyreport.model;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.*;

public class ReplyReportDAO implements ReplyReportDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G4";
	String passwd = "CEA101G4";

	private static final String INSERT_STMT = "INSERT INTO reply_report(report_id, emp_id, reply_id, mem_id, report_result) "
			+ "VALUES ('REP'|| LPAD(REPLY_REPORT_SEQ.NEXTVAL, 3, '0'), ?, ?, ?, ?)";
	private static final String DELETE = "DELETE FROM reply_report where report_id = ?";
	private static final String UPDATE = "UPDATE reply_report set emp_id=?, reply_id=?, mem_id=?, report_result=?"
			+ " where report_id = ?";
	private static final String GET_ONE_STMT = "SELECT emp_id, reply_id, mem_id, report_result FROM reply_report where report_id = ?";
	private static final String GET_ALL_STMT = "SELECT report_id, emp_id, reply_id, mem_id, report_result FROM reply_report "
			+ "order by report_id";

	@Override
	public void insert(ReplyReportVO replyReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, replyReportVO.getEmpId());
			pstmt.setString(2, replyReportVO.getReplyId());
			pstmt.setString(3, replyReportVO.getMemId());
			pstmt.setInt(4, replyReportVO.getReportResult());


			pstmt.executeUpdate();
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(ReplyReportVO replyReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, replyReportVO.getEmpId());
			pstmt.setString(2, replyReportVO.getReplyId());
			pstmt.setString(3, replyReportVO.getMemId());
			pstmt.setInt(4, replyReportVO.getReportResult());
			pstmt.setString(5, replyReportVO.getReportId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String reportId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, reportId);
			pstmt.executeUpdate();
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public ReplyReportVO findByPrimaryKey(String reportId) {
		ReplyReportVO replyReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, reportId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				replyReportVO = new ReplyReportVO();
				replyReportVO.setReportId(reportId);
				replyReportVO.setEmpId(rs.getString("emp_id"));
				replyReportVO.setReplyId(rs.getString("reply_id"));
				replyReportVO.setMemId(rs.getString("mem_id"));
				replyReportVO.setReportResult(rs.getInt("report_result"));
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return replyReportVO;
	}

	@Override
	public List<ReplyReportVO> getAll() {
		List<ReplyReportVO> list = new ArrayList<ReplyReportVO>();
		ReplyReportVO replyReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				replyReportVO = new ReplyReportVO();
				replyReportVO.setReportId(rs.getString("report_id"));
				replyReportVO.setEmpId(rs.getString("emp_id"));
				replyReportVO.setReplyId(rs.getString("reply_id"));
				replyReportVO.setMemId(rs.getString("mem_id"));
				replyReportVO.setReportResult(rs.getInt("report_result"));

				list.add(replyReportVO); // Store the row in the list
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}
