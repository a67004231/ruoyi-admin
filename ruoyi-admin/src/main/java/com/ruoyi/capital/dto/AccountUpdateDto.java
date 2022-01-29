package com.ruoyi.capital.dto;


public class AccountUpdateDto {
	/**账户修改资金*/
	private Long sysAmt;
	/**账户修改资金*/
	private Long merAmt;
	/**账户类型 1商户账户  2通道账户 3同时处理*/ 
	private int accountType;
	/**操作类型 1订单新增  2订单成功  3订单失败 4余额充值*/
	private int type;
	/**商户ID*/
	private Long merId;
	/**通道ID*/
	private Long channelId;
	/**订单ID*/
	private Long orderId;
	/**变更备注*/
	private String desc;

	

	public Long getSysAmt() {
		return sysAmt;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setSysAmt(Long sysAmt) {
		this.sysAmt = sysAmt;
	}

	public Long getMerAmt() {
		return merAmt;
	}

	public void setMerAmt(Long merAmt) {
		this.merAmt = merAmt;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getMerId() {
		return merId;
	}

	public void setMerId(Long merId) {
		this.merId = merId;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
}
