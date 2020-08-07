package com.j1.result;

/**
 * Created by wangchuanfu on 20/8/7.
 */
public class ResultMsg {
    /**
     * 公用消息
     *
     *
     */
    public static class Common {
        public static final String OK = "ok";
        public static final String MEMBER_KEY_EMPTY = "memberKey不能为空";
        public static final String QUERY_FAILURE = "查询失败";
        public static final String QUERY_NO_RESULT = "查询无结果";
        public static final String MOBILE_PATTERN_ERROR = "手机号格式错误!";
    }

    /**
     * 系统 SystemAction 消息
     */
    public static class SystemAction {
        public static final String KEYWORD_WRONG = "验证码错误";
    }

    /**
     * 优惠券Action消息
     *
     *
     */
    public static class CouponAction {
        public static final String LIST_FAILURE = "获取优惠券列表失败";
    }

    /**
     * 个人中心Action消息
     *
     *
     */
    public static class MemberCenterAction {
        public static final String INFO_FAILURE = "查询个人信息失败";
    }

    /**
     * 提交订单Action消息
     *
     *
     */
    public static class CommitOrderAction {
        public static final String INDEX_FAILURE = "初始化订单信息失败";
        public static final String POST_FAILURE = "提交订单失败";
    }

    /**
     * 消息中心Action消息
     *
     *
     */
    public static class MessageCenterAction {
        public static final String SEND_FAILURE = "发送消息失败";
        public static final String READ_MESSAGE = "读取消息失败";
    }

    /**
     * 用户Action消息
     *
     *
     */
    public static class UserActionMsg {
        public static final String LOGIN_FAILURE = "登录失败";
        public static final String REGISTER_FAILURE = "注册失败";
        public static final String FORGET_FAILURE = "忘记密码短信发送失败";
        public static final String RESET_FAILURE = "重置密码失败";
        public static final String CODE_EMPTY = "验证码不能为空";
        public static final String KEY_EMPTY = "Key不能为空";
        public static final String INVALID_KEY = "验证码失效,请重新发送";
        public static final String VERIFY_SUCCESS = "验证成功";
        public static final String VERIFY_CODE_WRONG = "验证码错误";
        public static final String MOBILE_NOT_MATCH_KEY = "手机号与Key不匹配";
        public static final String MOBILE_EMPTY = "手机号不能为空";
        public static final String MOBILE_EXISTS = "手机号已经被使用";
        public static final String REGISTER_SEND_FAILURE = "注册失败";
        public static final String FAST_LOGIN_FAILURE = "联合登陆失败";
        public static final String FIND_BY_LOGIN_NAME_FAILURE = "找回密码失败";
        public static final String SEND_EMAIL_FAILURE = "发送邮箱失败";
        public static final String MOBILE_CODE ="手机号或验证码不能为空";
        public static final String SEND_MOBILE_FAILURE = "发送验证码失败";
        public static final String QUICK_LOGIN_FAILURE = "快速登录失败";
        public static final String REGISTER_STORE_FAILURE = "门店录入失败";
    }

    /**
     * 会员Action消息
     *
     *
     */
    public static class MemberActionMsg {
        public static final String QUERY_FAILURE = "查询用户信息失败";
        public static final String SIGN_FAILURE = "签到失败";
        public static final String SUGGEST_FAILURE = "投诉建议提交失败";
        public static final String  QUERY_ERROR= "查询积分失败";
    }

    /**
     * 会员等级Action消息
     *
     *
     */
    public static class MemberRankActionMsg {
        public static final String QUERY_FAILURE = "会员等级信息查询失败";
    }

    /**
     * 促销Action消息
     *
     *
     */
    public static class PromoteAction {
        public static final String PROMOTE_LIST_FAILURE = "促销列表获取失败";
        public static final String HG_LIST_FAILURE = "加价购选项获取失败";
        public static final String PROMOTE_TYPE_EMPTY = "促销类型参数为空";
        public static final String PROMOTE_ID_WRONG = "促销ID参数错误";
        public static final String PROMOTE_TYPE_WRONG = "促销类型参数错误";
        public static final String HG_NOT_FOUND = "该加价购促销信息不存在";
        public static final String PROMOTE_ITEM_ID_EMPTY = "促销选项ID参数为空";
        public static final String CHOOSE_FAILURE = "选择促销商品失败";
        public static final String DEL_FAILURE = "删除促销失败";
    }

    /**
     * 活动Action消息
     *
     *
     */
    public static class ActivityActionMsg {
        public static final String LIST_FAILURE = "查询活动失败";
    }

    /**
     * 首页内容Action消息
     *
     *
     */
    public static class IndexActionMsg {
        public static final String QUERY_FAILURE = "查询失败";
        public static final String LOW_PRICE_FAILURE = "低价爆款获取失败";
        public static final String PROMOTE_GOODS_FAILURE = "单品促销获取失败";
        public static final String BANNER_FAILURE = "Banner获取失败";
        public static final String SORTS_FAILURE = "热门推荐获取失败";
        public static final String Approve_Failure = "钟意导航信息获取失败";
        public static final String Special_Parameter_Failure="参数为空或错误";
        public static final String Special_Failure="专题活动查询失败";

    }

    /**
     * 商品信息Action消息
     *
     *
     */
    public static class GoodsActionMsg {
        public static final String DETAIL_FAILURE = "商品详情查询失败";
        public static final String TC_FAILURE = "处方药需求登记失败";
        public static final String RECORD_FAILURE = "获取成交纪录失败";
        public static final String DH_REMIND = "到货提醒登记失败";
    }

    /**
     * 购物车Action消息
     *
     *
     */
    public static class ShopcartActionMsg {
        public static final String INIT_FAILURE = "获取购物车信息失败";
        public static final String MODIFY_FAILURE = "修改购物车失败";
        public static final String REMOVE_FAILURE = "删除购物车商品失败";
        public static final String LOGISTICS_FAILURE = "获取物流列表失败";
        public static final String CHECK_VOUCHER_FAILURE = "验证优惠券失败";
        public static final String EMPTY_FAILURE = "清空购物车失败";
    }

    /**
     * CommitOrder Action msg
     *
     *
     */
    public static class MakrUpOrderMsg {
        public static final String INIT_FAILURE = "获取凑单列表失败";
    }

    /**
     * 订单Action消息
     *
     *
     */
    public static class OrderActionMsg {
        public static final String LIST_FAILRE = "查询订单失败";
        public static final String LOGISTICS_FAILURE = "查询订单物流失败";
        public static final String CANCEL_FAILURE = "取消订单失败";
        public static final String LOG_FAILURE = "查询订单日志失败";
        public static final String DETAIL_FAILURE = "订单详情获取失败";
        public static final String CONFIRM_FAILURE = "确认收获失败";
        public static final String EXPECT_SIZE = "orderId参数错误";
        public static final String ORDER_ID_EMPTY = "缺少orderId参数";
        public static final String ORDER_NOT_EXISTS = "查询的订单不存在";
        public static final String INSERT_STORE_FAILURE= "门店订单数据导入失败";
    }

    /**
     * 商品评价Action消息
     *
     *
     */
    public static class GoodsCommentActionMsg {
        public static final String LIST_FAILURE = "获取商品评价失败";
        public static final String ADD_FAILURE = "添加评论失败";
        public static final String MODIFY_FAILURE = "更新评价失败";
        public static final String DEL_FAILURE = "删除评价失败";
    }

    /**
     * 商品分类Action消息
     *
     *
     */
    public static class CatalogActionMsg {
        public static final String LIST_FAILURE = "商品分类查询失败";
    }

    /**
     * 会员收货地址Action消息
     *
     *
     */
    public static class MemberAddressActionMsg {
        public static final String LIST_FAILURE = "查询收货地址失败";
        public static final String ADD_FAILURE = "添加收货地址失败";
        public static final String MODIFY_FAILURE = "修改收货地址失败";
        public static final String DELETE_FAILURE = "删除收货地址失败";
        public static final String ADDRESS_NO_RESULT = "收货地址不存在!";
        public static final String MEMBER_ADDRESS_ID_EMPTY = "memberAddressId参数不能为空";
    }

    public static class MemberAccountActionMsg {
        public static final String MEMBER_ID_EMPTY = "memberId不能为空";
        public static final String QUERY_FAILURE = "账户余额查询失败";
        public static final String Q_A_FAILURE = "安全问题或答案不能为空";
    }
    /**
     * 摇一摇Action消息
     *
     *
     */
    public static class ShakeActionMsg {
        public static final String TO_MAIN_FAILURE = "进入摇一摇失败";
        public static final String SHAKE_FAILURE = "摇一摇获取结果失败";
        public static final String Q_A_FAILURE ="安全问题或答案不能为空";
    }

    // public static class ShakeActionMsg {
    // public static final String MEMBER_ID_EMPTY = "memberId不能为空";
    // public static final String QUERY_FAILURE = "账户余额查询失败";
    // public static final String Q_A_FAILURE ="安全问题或答案不能为空";
    // }
    /**
     * CrcAction
     **
     */
    public static class CrcActionMsg {
        public static final String QUERY_HOT_PAGEINDEXIMG_FAILURE = "查询轮播图片失败";
        public static final String QUERY_HOT_PAGEINDEXIMG_EMPTY = "查询轮播图片结果集为空";
        public static final String QUERY_HOT_BRAND_FAILURE = "查询热销品牌失败";
        public static final String QUERY_HOT_BRAND_EMPTY = "查询热销品牌结果集为空";
        public static final String QUERY_LIMIT_TIME_BUY_FAILURE = "查询限时抢购失败";
        public static final String QUERY_LIMIT_TIME_BUY_EMPTY = "查询限时抢购结果集为空";
        public static final String PARAMS_FIRST_CATALOG_EMPTY = "一级分类id为空";
        public static final String PARAMS_FIRST_CATALOG_ERROR = "一级分类id参数有误";
        public static final String PARAMS_SECOND_CATALOG_ERROR = "二级分类id参数有误";
        public static final String PARAMS_CURRENT_PAGE_ERROR = "当前页参数有误";
        public static final String QUERY_GOODS_CATALOG_FAILURE = "查询分类目录失败";
        public static final String QUERY_GOODS_CATALOG_EMPTY = "查询分类目录失败结果集为空";
        public static final String QUERY_CATALOG_GOODS_FAILURE = "分类查询商品失败";
        public static final String QUERY_CATALOG_GOODS_EMPTY = "分类查询商品结果集为空";
        public static final String PARAMS_DEFALUT_FIRST_CATALOG_EMPTY = "默认一级分类id为空";

    }

    /**
     * SubMallAction
     * 越测越开心
     *
     */
    public static class SubMallAction {
        public static final String SUB_MALL_LIST_FAILURE = "越策越开心列表获取失败";
        public static final String SUB_MALL_DETAIL_FAILURE = "越策越开心详情获取失败";
    }
    /**
     * PointsMall
     * 积分商城
     *
     */
    public static class PointsMall {
        public static final String TO_FREE_COUPON_MAIN_FAILURE = "积分兑换优惠券活动查询失败";
        public static final String TO_FREE_PROMOTE_MAIN_FAILURE = "积分兑换商品活动查询失败";
        public static final String POINTS_BUY_COUPON__FAILURE ="积分兑换优惠券失败";
    }
    public static class MemberShareActivityMsg {
        public static final String INIT_FAILURE = "初始化失败";
//		public static final String INIT_FAILURE = "初始化失败";
//		public static final String INIT_FAILURE = "初始化失败";
//		public static final String INIT_FAILURE = "初始化失败";
//		public static final String INIT_FAILURE = "初始化失败";
    }

    public static class WeixinActionMsg{
        public static final String WEIXIN_FAILURE = "微信接口查询失败";
    }
    /**
     * 门店录入成功
     * @author wangchuanfu
     *
     */
    public static class StoreSendMsg{
        public static final String  MESSAGE_SEND= "恭喜你成为健一网会员,下载健一网APP后,可以直接使用手机号码登录,如有疑问请拔致电4007-800-800";
        public static final String  INSERT_FALSE= "录入失败";
    }

    /**
     * 太平用户联合登录消息

     */
    public static class MemberTpActionMsg {
        public static final String PARAMS_EMPTY = "参数不能为空!";
        public static final String PARAMS_CODE_EMPTY = "验证码不能为空";
        public static final String PARAMS_MUL_EMPTY = "请求来源不正确";
        public static final String TP_EXCEPTION = "网络异常";
        public static final String TP_VERIFY_ERROR = "验证失败，不是太平用户";
        public static final String TP_BINGING_ERROR = "太平用户绑定失败";
        public static final String TP_LOGIN_ERROR = "太平用户登录失败";
        public static final String PARAMS_VERIFY_CODE_ERROR = "验证码错误";
        public static final String PARAMS_MEMBERKEY_INVALID = "无效的memberKey";
        public static final String NORMAL ="ok";
    }
}
