package com.foodspot.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FoodSpotDAO implements FoodSpotDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT =
			"INSERT INTO FOOD_SPOT(FAS_ID, SELL_MEM_ID, FAS_SPOT_NAME, FAS_ADD, FAS_DES, FAS_PHOTO, FAS_LATITUDE, FAS_LONGITUD)"
			+ " VALUES ('FAS' || LPAD(FAS_SEQ.NEXTVAL, 3, '0'),?,?,?,?,?,?,?)";
	private static final String UPDATE = 
			"UPDATE FOOD_SPOT set SELL_MEM_ID=?,FAS_SPOT_NAME=?,FAS_ADD=?,FAS_DES=?, FAS_PHOTO=?, FAS_LATITUDE=?,FAS_LONGITUD=? where FAS_ID=?";
	private static final String DELETE = 
			"DELETE FROM FOOD_SPOT where FAS_ID = ?";
	private static final String GET_ONE_STMT =
			"SELECT FAS_ID, SELL_MEM_ID, FAS_SPOT_NAME, FAS_ADD, FAS_DES, FAS_PHOTO, FAS_LATITUDE, FAS_LONGITUD FROM Food_Spot where FAS_ID = ?";
	private static final String GET_BY_SELL =
			"SELECT FAS_ID, FAS_SPOT_NAME, FAS_ADD, FAS_DES, FAS_PHOTO, FAS_LATITUDE, FAS_LONGITUD FROM Food_Spot where SELL_MEM_ID = ?";
	private static final String GET_ALL_STMT =
			"SELECT FAS_ID, SELL_MEM_ID, FAS_SPOT_NAME, FAS_ADD, FAS_DES, FAS_PHOTO, FAS_LATITUDE, FAS_LONGITUD FROM Food_Spot order by FAS_ID";
//	private static final String GET_ONE_FAS_PHOTO =
//			"SELECT FAS_PHOTO FROM Food_Spot where FAS_ID=?";
	@Override
	public void insert(FoodSpotVO fsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setString(1, fsVO.getSell_mem_id());
			pstmt.setString(2, fsVO.getFas_spot_name());
			pstmt.setString(3, fsVO.getFas_add());
			pstmt.setString(4, fsVO.getFas_des());
			pstmt.setBytes(5, fsVO.getFas_photo());
			pstmt.setDouble(6, fsVO.getFas_latitude());
			pstmt.setDouble(7, fsVO.getFas_longitud());
			
			pstmt.executeUpdate();

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
	public void update(FoodSpotVO fsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, fsVO.getSell_mem_id());
			pstmt.setString(2, fsVO.getFas_spot_name());
			pstmt.setString(3, fsVO.getFas_add());
			pstmt.setString(4, fsVO.getFas_des());
			pstmt.setBytes(5, fsVO.getFas_photo());
			pstmt.setDouble(6, fsVO.getFas_latitude());
			pstmt.setDouble(7, fsVO.getFas_longitud());
			pstmt.setString(8, fsVO.getFas_id());
			

			pstmt.executeUpdate();

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
	public void delete(String fas_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,fas_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public FoodSpotVO findByPrimaryKey(String fas_id) {

		FoodSpotVO fsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, fas_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				fsVO = new FoodSpotVO();
				fsVO.setFas_id(rs.getString("fas_id"));
				fsVO.setSell_mem_id(rs.getString("sell_mem_id"));
				fsVO.setFas_spot_name(rs.getString("fas_spot_name"));
				fsVO.setFas_add(rs.getString("fas_add"));
				fsVO.setFas_des(rs.getString("fas_des"));
				fsVO.setFas_photo(rs.getBytes("fas_photo"));
				fsVO.setFas_latitude(rs.getDouble("fas_latitude"));
				fsVO.setFas_longitud(rs.getDouble("fas_longitud"));
			}

			// Handle any driver errors
					} catch (SQLException se) {
						throw new RuntimeException("A database error occured. "
								+ se.getMessage());
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
					return fsVO;
				}

	@Override
	public List<FoodSpotVO> findBySell(String sell_mem_id) {
		List<FoodSpotVO> list = new ArrayList<FoodSpotVO>();
		FoodSpotVO fsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_SELL);

			pstmt.setString(1, sell_mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				fsVO = new FoodSpotVO();
				fsVO.setFas_id(rs.getString("fas_id"));	
				fsVO.setSell_mem_id(rs.getString("sell_mem_id"));
				fsVO.setFas_spot_name(rs.getString("fas_spot_name"));
				fsVO.setFas_add(rs.getString("fas_add"));
				fsVO.setFas_des(rs.getString("fas_des"));
				fsVO.setFas_photo(rs.getBytes("fas_photo"));
				fsVO.setFas_latitude(rs.getDouble("fas_latitude"));
				fsVO.setFas_longitud(rs.getDouble("fas_longitud"));
				list.add(fsVO);
			}

			// Handle any driver errors
					} catch (SQLException se) {
						throw new RuntimeException("A database error occured. "
								+ se.getMessage());
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



	@SuppressWarnings("null")
	@Override
	public List<FoodSpotVO> getAll() {
		List<FoodSpotVO> list = new ArrayList<FoodSpotVO>();
		FoodSpotVO fsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				fsVO = new FoodSpotVO();
				fsVO.setFas_id(rs.getString("fas_id"));
				fsVO.setSell_mem_id(rs.getString("sell_mem_id"));
				fsVO.setFas_spot_name(rs.getString("fas_spot_name"));
				fsVO.setFas_add(rs.getString("fas_add"));
				fsVO.setFas_des(rs.getString("fas_des"));
				fsVO.setFas_photo(rs.getBytes("fas_photo"));
				fsVO.setFas_latitude(rs.getDouble("fas_latitude"));
				fsVO.setFas_longitud(rs.getDouble("fas_longitud"));
				list.add(fsVO);
			}

			// Handle any driver errors
					} catch (SQLException se) {
						throw new RuntimeException("A database error occured. "
								+ se.getMessage());
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



//	@Override
//	public FoodSpotVO getFasPhoto(String fas_id) {
//		FoodSpotVO fsVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setString(1, fas_id);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//			
//				fsVO = new FoodSpotVO();
//				fsVO.setFas_id(rs.getString("fas_id"));
//				fsVO.setSell_mem_id(rs.getString("sell_mem_id"));
//				fsVO.setFas_spot_name(rs.getString("fas_spot_name"));
//				fsVO.setFas_add(rs.getString("fas_add"));
//				fsVO.setFas_des(rs.getString("fas_des"));
//				fsVO.setFas_photo(rs.getBytes("fas_photo"));
//				fsVO.setFas_latitude(rs.getDouble("fas_latitude"));
//				fsVO.setFas_longitud(rs.getDouble("fas_longitud"));
//			
//			}
//
//			// Handle any driver errors
//					} catch (SQLException se) {
//						throw new RuntimeException("A database error occured. "
//								+ se.getMessage());
//						// Clean up JDBC resources
//					} finally {
//						if (rs != null) {
//							try {
//								rs.close();
//							} catch (SQLException se) {
//								se.printStackTrace(System.err);
//							}
//						}
//						if (pstmt != null) {
//							try {
//								pstmt.close();
//							} catch (SQLException se) {
//								se.printStackTrace(System.err);
//							}
//						}
//						if (con != null) {
//							try {
//								con.close();
//							} catch (Exception e) {
//								e.printStackTrace(System.err);
//							}
//						}
//					}
//					return fsVO;
//	
//	}
	
		
	
}
