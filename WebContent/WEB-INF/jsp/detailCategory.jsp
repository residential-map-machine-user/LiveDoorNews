<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Beans.ItemBean"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<%
		String uri = request.getRequestURI();
	%>
	<%
		ArrayList<ItemBean> itemList = new ArrayList<ItemBean>();
		String action = (String) request.getAttribute("action");
		itemList = (ArrayList<ItemBean>) request.getAttribute(action);
	%>
	<div class="content">
		<div class="w-section section" id="about">
			<div class="w-container">
				<div class="section-title-group">
					<h2 class="section-heading centered"><%=action%>&nbsp;&nbsp;&nbsp;News
					</h2>
					<div class="section-subheading center">broadcasted latest
						news</div>
				</div>
				<%
					for (ItemBean item : itemList) {
				%>
				<div class="col-spc-wrapper">
					<div class="col-spc">
						<h1><%=item.getTitle()%></h1>
						<%
							if (item.getDescription().equals("記事を読む")) {
						%>

						<a href="<%=item.getLink()%>">Text Link</a>
						<%
							} else {
						%>
						<p><%=item.getDescription()%></p>
						<a href="<%=item.getLink()%>">Text Link</a>
						<%
							}
						%>

						<%
							List<String> relatedLink = item.getRelatedLinks();
								if (relatedLink != null && relatedLink.size() > 0) {
						%>
						<h1>関連記事</h1>
						<div class="w-row">
							<%
								for (int i = 0; i < relatedLink.size(); i++) {
							%>
							<div class="w-col w-col-3 col-spc">
								<img class="grid-image" src="images/feather-05-black.svg"
									width="80">
								<h3>
									記事<%=i + 1%></h3>
								<a class="link" href="<%=relatedLink.get(0)%>">Text Link</a>
							</div>
							<%
								}
							%>
						</div>
					</div>
				</div>
				<%
					}
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