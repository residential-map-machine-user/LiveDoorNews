<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Beans.ItemBean"%>
<%@ page import="java.util.*"%>
<%@ page import="Constants.*"%>
<%@ page import="Utils.*"%>

<!DOCTYPE html>
<html>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />

	<%
		Util.l("ok1");
		String actionName = (String) request.getAttribute("ACTION");
		Util.l("ok2" + actionName);
		ArrayList<ItemBean> contents = new ArrayList<ItemBean>();
		Util.l("ok3");
		contents = (ArrayList<ItemBean>) request.getAttribute(actionName);
		Util.l("ok4");
	%>
	<div class="content">
		<div class="w-section section" id="about">
			<div class="w-container">
				<div class="section-title-group">
					<h2 class="section-heading centered"><%=actionName%>&nbsp;&nbsp;&nbsp;News
					</h2>
					<div class="section-subheading center">broadcasted latest
						news</div>
				</div>
				<%
					if (contents != null) {
						for (ItemBean item : contents) {
				%>
				<div class="col-spc-wrapper">
					<div class="col-spc">
						<h1><%=item.getTitle()%></h>
							<p><%=item.getArticle()%></p>
							<a  class="link" href="<%=item.getUrl()%>">掲載元のページへ</a>


							<%
								List<ItemBean> relatedLink = item.getRelatedLink();
							%>
							<h1>関連記事</h1>
							<div class="w-row">
								<%
									if (relatedLink != null) {
												for (int i = 3; i < relatedLink.size(); i=i+3) {
								%>
								<div class="w-col w-col-3 col-spc">
									<img class="grid-image" src="<%=request.getContextPath()%>/res/images/feather-05-black.svg"
										width="80">
									<h6><%=relatedLink.get(i).getTitle() %></h3>
									<a class="link"style="text-decoration:none;font-size:10px;" href="<%=relatedLink.get(i).getUrl()%>">掲載元のページへ</a>
								</div>
								<%
									}
								%>
								<%
									} else {
								%>
								<p class="col-3">通信エラーです<p>
								<%
									}
								%>
							</div>
					</div>
				</div>
				<%
					}
					} else {
				%>
				<%
					}
				%>
			</div>
		</div>
		<div class="w-section section section-gray" id="contact"></div>
		<footer class="w-section footer center">
			<div class="w-container">
				<div class="footer-text">Copyright Incredible. Made in
					Webflow.</div>
			</div>
		</footer>
	</div>
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<!--[if lte IE 9]><script src="https://cdnjs.cloudflare.com/ajax/libs/placeholders/3.0.2/placeholders.min.js"></script><![endif]-->
</body>
</html>