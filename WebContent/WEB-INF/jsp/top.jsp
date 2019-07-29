<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport"
			content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
		<title>高校本科毕业设计管理系统</title>
		
		<script type="text/javascript" src="/bysj/js/jquery-1.8.3.min.js"></script>
		<!-- <script type="text/javascript" src="js/js_z.js"></script> -->
		<script type="text/javascript" src="/bysj/js/js_z.js"></script>
		<script type="text/javascript"src="/bysj/js/jquery.flexslider.js"></script>
		
		<link rel="stylesheet" type="text/css" href="/bysj/css/flexslider.css">
		<link rel="stylesheet" type="text/css" href="/bysj/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="/bysj/css/thems.css">
		<link rel="stylesheet" type="text/css" href="/bysj/css/responsive.css">
		
		<script language="javascript">
$(window).load(function() {
	$('.flexslider').flexslider( {
		animation : "slide"
	});
});
</script>
	</head>

	<body>

		<!--头部-->
		<div class="t_bg">
			<div class="top">
	
				<div style="float: left;margin-left:159; color: #ACD6FF;">

				</div>
				<div style="float: right;margin-right:19; text-align: right;">

					<c:if test="${empty sessionUser.sName}">
						<a href="toLogin">登录</a>
					</c:if>
					<c:if test="${!empty sessionUser.sName}">
					当前用户:<a style="color:#a2b5cd;"href="Student/queryInfo">${sessionUser.sName}</a>
		&nbsp; |&nbsp;<a href="Student/exitLogin">退出登录</a>
					</c:if>

				</div>
			</div>

		</div>
		<div class="head clearfix">
			<div class="logo">
				<img src="images/logo4.png" style="width:341px;height:106px;margin-top:-47px;margin-left:-21px" />
			</div>
			<!-- <form action="" method="post" style="margin-left:461;"class="search">
				<input style="border-radius:0;width:328;"name="search" type="text" placeholder="请输入关键字">
				<input name="" type="submit">
			</form> -->

		</div>
		<div class="n_bg">
			<div class="nav_m">
				<div class="n_icon">
					导航栏
				</div>
				<ul class="nav clearfix">
					<li style="margin-left:20;">
						<a href="index.jsp"><i>&nbsp;</i>首页</a>
					</li>
					<li>
						<a href="http://www.xysfxy.cn/info/iList.jsp?cat_id=10002"><i>&nbsp;</i>学校概况</a>
						<div class="er clearfix">
							
						</div>
					</li>
					<li>
						<a href="http://www.xysfxy.cn/info/iList.jsp?cat_id=10042"><i>&nbsp;</i>走进师院</a>
						<div class="er clearfix">
							
						</div>
					</li>
					<li>
						<a href="http://www.xysfxy.cn/info/iList.jsp?cat_id=10998"><i>&nbsp;</i>机构设置</a>
						<div class="er clearfix">
							

						</div>
					</li>
					<li>
						<a href="http://www.xysfxy.cn/info/iList.jsp?cat_id=10012"><i>&nbsp;</i>教育教学</a>
						<div class="er clearfix">
							
						</div>
					</li>

					<li>
						<a href="http://www.xysfxy.cn/info/iList.jsp?cat_id=10021"><i>&nbsp;</i>科学研究</a>
						<div class="er clearfix">

						</div>
					</li>
					<li>
						<a href="http://www.xysfxy.cn/"><i>&nbsp;</i>团队介绍</a>
						<div class="er clearfix">
							<a href="">网站介绍</a>
							<a href="">团队介绍</a>
							<a href="">团队文化</a>

						</div>
					</li>
					<li>
						<a href=""><i>&nbsp;</i>联系我们</a>
						<div class="er clearfix">
							<a href="">联系我们</a>
							<a href="">人才招聘</a>
							<a href="">用户留言</a>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<!--头部-->
		<!--幻灯片-->
		<div style="width:960px;height:350px;border:1px solid #e0e0e0;" class="banner">
			<div class="slider">
				<div class="flexslider">
					<ul class="slides">
						<li>
							<a href=""><img src="/bysj/images/nav1.jpg" alt="图1" />
							</a>
						</li>
						<li>
							<a href=""><img src="/bysj/images/nav2.jpg" alt="图2" />
							</a>
						</li>
						<li>
							<a href=""><img src="/bysj/images/nav3.jpg" alt="图3" />
							</a>
						</li>
						<li>
							<a href=""><img src="/bysj/images/nav4.jpg" alt="图4" />
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>

		<div class="space_hx">
			&nbsp;
		</div>
		<!-- 通知、公告 -->
		<div class="i_mc clearfix">
			<div class="i_mcl">
				<div class="box_h">
					<span> <em>通知公告</em> <i>&nbsp;</i> </span>
					<a href="">+ MORE</a>
				</div>
				<div class="box_m">
			
					<ul class="clearfix">
						<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=64">
								<p>
									关于2017级新生学籍建档的通知
								</p> </a>
						</li>
						<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=63">
								<p>
									关于2018届毕业生图像信息采集工作的通知
								</p> </a>
						</li>
						<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=62">
								<p>
									关于做好2017级新生学籍电子注册工作的通知
								</p> </a>
						</li>
						<li>
                			<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=59">
                    			<p>关于做好2017年9月全国计算机等级考试报名工作的通知 </p>
                    
                    </a>
                </li>
             	<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=64">
								<p>
									关于2017级新生学籍建档的通知
								</p> </a>
						</li>
						<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=63">
								<p>
									关于2018届毕业生图像信息采集工作的通知
								</p> </a>
						</li>
						<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=62">
								<p>
									关于做好2017级新生学籍电子注册工作的通知
								</p> </a>
						</li>
						<li>
                			<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=59">
                    			<p>关于做好2017年9月全国计算机等级考试报名工作的通知 </p>
                    
                    </a>
                </li>

					</ul>
				</div>
			</div>
			<div class="i_mcr">
				<div class="box_h">
					<span> <em>管理规定</em> <i>&nbsp;</i> </span>
					<a href="http://www.btbu.edu.cn/news/index.htm">+ MORE</a>
				</div>
				<div class="box_m">
					
					<ul class="clearfix">
						<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=64">
								<p>
									关于2017级新生学籍建档的通知
								</p> </a>
						</li>
						<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=63">
								<p>
									关于2018届毕业生图像信息采集工作的通知
								</p> </a>
						</li>
						<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=62">
								<p>
									关于做好2017级新生学籍电子注册工作的通知
								</p> </a>
						</li>
						<li>
                			<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=59">
                    			<p>关于做好2017年9月全国计算机等级考试报名工作的通知 </p>
                    
                    </a>
                </li>
             	<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=64">
								<p>
									关于2017级新生学籍建档的通知
								</p> </a>
						</li>
						<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=63">
								<p>
									关于2018届毕业生图像信息采集工作的通知
								</p> </a>
						</li>
						<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=62">
								<p>
									关于做好2017级新生学籍电子注册工作的通知
								</p> </a>
						</li>
						<li>
                			<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=59">
                    			<p>关于做好2017年9月全国计算机等级考试报名工作的通知 </p>
                    
                    </a>
                </li>

					</ul>
					
				</div>
			</div>
		</div>
		<!-- 新闻、活动 -->
		<div class="i_mc clearfix">
			<div class="i_mcl">
				<div class="box_h">
					<span> <em>教务安排</em> <i>&nbsp;</i> </span>
					<a href="">+ MORE</a>
				</div>
				<div class="box_m">
					<ul class="clearfix">
						<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=64">
								<p>
									关于2017级新生学籍建档的通知
								</p> </a>
						</li>
						<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=63">
								<p>
									关于2018届毕业生图像信息采集工作的通知
								</p> </a>
						</li>
						<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=62">
								<p>
									关于做好2017级新生学籍电子注册工作的通知
								</p> </a>
						</li>
						<li>
                			<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=59">
                    			<p>关于做好2017年9月全国计算机等级考试报名工作的通知 </p>
                    
                    </a>
                </li>
             	<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=64">
								<p>
									关于2017级新生学籍建档的通知
								</p> </a>
						</li>
						<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=63">
								<p>
									关于2018届毕业生图像信息采集工作的通知
								</p> </a>
						</li>
						<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=62">
								<p>
									关于做好2017级新生学籍电子注册工作的通知
								</p> </a>
						</li>
						<li>
                			<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=59">
                    			<p>关于做好2017年9月全国计算机等级考试报名工作的通知 </p>
                    
                    </a>
                </li>

					</ul>
				</div>
			</div>
			<div class="i_mcr">
				<div class="box_h">
					<span> <em>毕业相关</em> <i>&nbsp;</i> </span>
					<a href="http://www.btbu.edu.cn/news/index.htm">+ MORE</a>
				</div>
				<div class="box_m">
					<ul class="clearfix">
						<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=64">
								<p>
									关于2017级新生学籍建档的通知
								</p> </a>
						</li>
						<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=63">
								<p>
									关于2018届毕业生图像信息采集工作的通知
								</p> </a>
						</li>
						<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=62">
								<p>
									关于做好2017级新生学籍电子注册工作的通知
								</p> </a>
						</li>
						<li>
                			<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=59">
                    			<p>关于做好2017年9月全国计算机等级考试报名工作的通知 </p>
                    
                    </a>
                </li>
             	<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=64">
								<p>
									关于2017级新生学籍建档的通知
								</p> </a>
						</li>
						<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=63">
								<p>
									关于2018届毕业生图像信息采集工作的通知
								</p> </a>
						</li>
						<li>
							<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=62">
								<p>
									关于做好2017级新生学籍电子注册工作的通知
								</p> </a>
						</li>
						<li>
                			<a href="http://218.195.80.4/_data/read_detail_news.aspx?tid=59">
                    			<p>关于做好2017年9月全国计算机等级考试报名工作的通知 </p>
                    
                    </a>
                </li>

					</ul>
					
				</div>
			</div>
		</div>
		<div class="space_hx">
			&nbsp;
		</div>
		<div class="bg_b">
			<div class="f_nav clearfix">
				<div class="f_nl">
					<div class="name">
						友情链接
					</div>
					<div class="frd">
						<a href="http://www.bnu.edu.cn/">北京师范大学</a>
						<br />
						<a href="http://www.snnu.edu.cn/">陕西师范大学</a>
						<a href="http://www.wntc.edu.cn/">渭南师范学院</a>	
					</div>
				</div>
				
			</div>
		</div>
		
	</body>

</html>
