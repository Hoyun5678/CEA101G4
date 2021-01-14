<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="actproSvc" scope="page" class="com.activity_product.model.ActivityProductService" />
<jsp:useBean id="actordSvc" scope="page" class="com.activity_order.model.ActivityOrderService" />
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />


<c:set var='sell_mem_id' scope="page" value='${param.sell_mem_id}' />
<c:set var='act_start_date' scope="page" value='${param.act_start_date}' />
<c:set var='actordList' scope='page' value='${actordSvc.getActOrdrBySellMemIdAndDate(sell_mem_id, act_start_date)}' />
	<c:forEach var="actordVO" items="${actordList}" varStatus="userStatus">
		<div class="card">
			<div class="card-header" id="headingOne">
				<h5 class="mb-0">
					<button class="btn btn-link" data-toggle="collapse" data-target="#collapse${userStatus.count}" aria-expanded="true" aria-controls="collapse${userStatus.count}" style="text-decoration: none;" >
						訂購者: ${memSvc.getOneMem(actordVO.mem_id).mem_name}
						
					</button>
				</h5>
			</div>
	
			<div id="collapse${userStatus.count}" class="collapse" aria-labelledby="headingOne">
				<div class="card-body">
					<div>報名人數: ${actordVO.act_order_amount}</div>
					<div>訂單金額: ${actordVO.act_sum_price}</div>
					<div>付款狀態: ${actordVO.act_payment_status== 2 ? '已付款': '未付款'}</div>
					<div>訂單備註: ${actordVO.act_order_remarks}</div>
					 
					
					<div><button class="btn btn-secondary d-flex ml-auto">已完成</button></div>
				
				</div>
			</div>
		</div>
	</c:forEach>

