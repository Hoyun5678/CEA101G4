package com.reply.model;

import java.sql.*;
import java.util.*;

import util.Util;

public class ReplyJDBCDAO implements ReplyDAO_interface {
	String driver = Util.DRIVER;
	String url = Util.URL;
	String userid = Util.USER;
	String passwd = Util.PASSWORD;

	private static final String INSERT_STMT = 
			"INSERT INTO reply(reply_id, act_period_id, mem_id, reply_content, reply_visible) "
			+ "VALUES ('RPL' || LPAD(RPL_SEQ.NEXTVAL, 3, '0'), ?, ?, ?, ?)";
	private static final String DELETE = "DELETE FROM reply where reply_id = ?";
	private static final String UPDATE = 
			"UPDATE reply set act_period_id=?, mem_id=?, reply_content=?, reply_time=?, reply_visible=?"
			+ " where reply_id = ?";
	private static final String GET_ONE_STMT = 
			"SELECT act_period_id, mem_id,reply_content, reply_time, reply_visible "
			+ "FROM reply where reply_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT reply_id, act_period_id, mem_id,reply_content, reply_time, reply_visible "
					+ "FROM reply order by reply_id";
	@Override
	public void insert(ReplyVO replyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, replyVO.getActPeriodId());
			pstmt.setString(2, replyVO.getMemId());
			pstmt.setString(3, replyVO.getReplyContent());
			pstmt.setInt(4, replyVO.getReplyVisible());
			
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
	public void update(ReplyVO replyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, replyVO.getActPeriodId());
			pstmt.setString(2, replyVO.getMemId());
			pstmt.setString(3, replyVO.getReplyContent());
			pstmt.setTimestamp(4, replyVO.getReplyTime());
			pstmt.setInt(5, replyVO.getReplyVisible());
			pstmt.setString(6, replyVO.getReplyId());
			
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
	public void delete(String replyId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, replyId);
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
	public ReplyVO findByPrimaryKey(String replyId) {
		ReplyVO replyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, replyId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				replyVO = new ReplyVO();
				replyVO.setReplyId(replyId);
				replyVO.setActPeriodId(rs.getString("act_period_id"));
				replyVO.setMemId(rs.getString("mem_id"));
				replyVO.setReplyContent(rs.getString("reply_content"));
				replyVO.setReplyTime(rs.getTimestamp("reply_time"));
				replyVO.setReplyVisible(rs.getInt("reply_visible"));
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
		return replyVO;

	}

	@Override
	public List<ReplyVO> getAll() {
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		ReplyVO replyVO = null;

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
				replyVO = new ReplyVO();
				replyVO.setReplyId(rs.getString("reply_id"));
				replyVO.setActPeriodId(rs.getString("act_period_id"));
				replyVO.setMemId(rs.getString("mem_id"));
				replyVO.setReplyContent(rs.getString("reply_content"));
				replyVO.setReplyTime(rs.getTimestamp("reply_time"));
				replyVO.setReplyVisible(rs.getInt("reply_visible"));
				list.add(replyVO);
				// Store the row in the list
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

		ReplyJDBCDAO dao = new ReplyJDBCDAO();
//
//		// 新增
		ReplyVO replyVO1 = new ReplyVO();
		replyVO1.setActPeriodId("AP003");
		replyVO1.setMemId("MEM002");
		replyVO1.setReplyVisible(0);
		replyVO1.setReplyContent("這活動辦得太失敗了");
		dao.insert(replyVO1);
		
		// 修改
//		ReplyVO replyVO2 = new ReplyVO();
//		replyVO2.setReplyId("RPL00");
//		replyVO2.setActPeriodId("AP001");
//		replyVO2.setMemId("MEM001");
//		replyVO2.setReplyVisible(1);
//		replyVO2.setReplyContent("讚讚");
//		dao.update(replyVO2);

		
		// 刪除
//		dao.delete("RPL005");

		// 查詢
//		ReplyVO replyVO3 = dao.findByPrimaryKey("RPL001");
//		System.out.print("PKey="+ replyVO3.getReplyId() + "/");
//		System.out.print(replyVO3.getActPeriodId() + "/");
//		System.out.print(replyVO3.getMemId() + "/");
//		System.out.print(replyVO3.getReplyContent() + "/");
//		System.out.print(replyVO3.getReplyTime() + "/");
//		System.out.print(replyVO3.getReplyVisible() + "/");
//		System.out.println();
//		System.out.println("---------------------");
////
//		// 查詢
//		List<ReplyVO> list = dao.getAll();
//		for (ReplyVO aRep : list) {
//			System.out.print(aRep.getReplyId() + "/");
//			System.out.print(aRep.getActPeriodId() + "/");
//			System.out.print(aRep.getMemId() + "/");
//			System.out.print(aRep.getReplyContent() + "/");
//			System.out.print(aRep.getReplyTime() + "/");
//			System.out.print(aRep.getReplyVisible() + "/");	
//			System.out.println();
//			System.out.println("-------------------------------------");
//		}
//		
	}
}
