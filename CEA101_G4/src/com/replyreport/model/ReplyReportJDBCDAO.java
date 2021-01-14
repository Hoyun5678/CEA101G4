package com.replyreport.model;
import java.sql.*;
import java.util.*;
import com.replyreport.model.ReplyReportVO;

import util.Util;

public class ReplyReportJDBCDAO implements ReplyReportDAO_interface{
	String driver = Util.DRIVER;
	String url = Util.URL;
	String userid = Util.USER;
	String passwd = Util.PASSWORD;
	
	private static final String INSERT_STMT = 
			"INSERT INTO reply_report(report_id, emp_id, reply_id, mem_id, report_result) "
			+ "VALUES ('REP'|| LPAD(REPLY_REPORT_SEQ.NEXTVAL, 3, '0'), ?, ?, ?, ?)";
	private static final String DELETE = "DELETE FROM reply_report where report_id = ?";
	private static final String UPDATE = 
			"UPDATE reply_report set emp_id=?, reply_id=?, mem_id=?, report_result=?" 
			+ " where report_id = ?";
	private static final String GET_ONE_STMT = 
			"SELECT emp_id, reply_id, mem_id, report_result FROM reply_report where report_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT report_id, emp_id, reply_id, mem_id, report_result FROM reply_report "
			+ "order by report_id";

	@Override
	public void insert(ReplyReportVO replyReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, replyReportVO.getEmpId());
			pstmt.setString(2, replyReportVO.getReplyId());
			pstmt.setString(3, replyReportVO.getMemId());
			pstmt.setInt(4, replyReportVO.getReportResult());

			
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, replyReportVO.getEmpId());
			pstmt.setString(2, replyReportVO.getReplyId());
			pstmt.setString(3, replyReportVO.getMemId());
			pstmt.setInt(4, replyReportVO.getReportResult());
			pstmt.setString(5, replyReportVO.getReportId());
			
			pstmt.executeUpdate();
			
			// Handle any SQL errors
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, reportId);
			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());			
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
	
	public static void main(String[] args) {

		ReplyReportJDBCDAO dao = new ReplyReportJDBCDAO();
		
//		 新增
		ReplyReportVO replyReportVO1 = new ReplyReportVO();
		replyReportVO1.setEmpId("EMP003");
		replyReportVO1.setReplyId("RPL001");
		replyReportVO1.setMemId("MEM002");
		replyReportVO1.setReportResult(0);
		dao.insert(replyReportVO1);
		
		// 修改
//		ReplyReportVO replyReportVO2 = new ReplyReportVO();
//		replyReportVO2.setReportId("REP003");
//		replyReportVO2.setEmpId("EMP001");
//		replyReportVO2.setReplyId("RPL001");
//		replyReportVO2.setMemId("MEM001");
//		replyReportVO2.setReportResult(1);
//
//		dao.update(replyReportVO2);

		
		// 刪除
//		dao.delete("REP004");

		// 查詢
//		ReplyReportVO replyReportVO3 = dao.findByPrimaryKey("REP001");
//		System.out.print("PKey:"+ replyReportVO3.getReportId() + "/");
//		System.out.print(replyReportVO3.getEmpId() + "/");
//		System.out.print(replyReportVO3.getReplyId() + "/");
//		System.out.print(replyReportVO3.getMemId() + "/");
//		System.out.print(replyReportVO3.getReportResult()+ "/");
//		System.out.println();
//		System.out.println("------------------------------------");
////
//		// 查詢
//		List<ReplyReportVO> list = dao.getAll();
//		for (ReplyReportVO aRpRp : list) {
//			System.out.print(aRpRp.getReportId() + "/");
//			System.out.print(aRpRp.getEmpId() + "/");
//			System.out.print(aRpRp.getReplyId() + "/");
//			System.out.print(aRpRp.getMemId() + "/");
//			System.out.print(aRpRp.getReportResult() + "/");
//
//			System.out.println();
//			System.out.println("-------------------------------------");
//		}
	}

	@Override
	public List<ReplyReportVO> findByMemId(String memId) {
		// TODO Auto-generated method stub
		return null;
	}

}
