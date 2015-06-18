<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Beans.ItemBean" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<!-- This site was created in Webflow. http://www.webflow.com-->
<!-- Last Published: Mon Apr 20 2015 01:31:28 GMT+0000 (UTC) -->

<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp"/>
	<% ArrayList<ItemBean> itemList = new ArrayList<ItemBean>();
	itemList = (ArrayList<ItemBean>)request.getAttribute("food"); %>
  <div class="content">
    <div class="banner" id="home">
      <div class="hero-title-wrapper">
        <h1 class="hero-heading">Choose Your news</h1>
      </div>
    </div>
    <div class="w-section section" id="about">
      <div class="w-container">
        <div class="section-title-group">
          <h2 class="section-heading centered">NEws List</h2>
          <div class="section-subheading center">broadcasted latest news</div>
        </div>
        <div class="w-row">
          <div class="w-col w-col-3 col-spc"><img class="grid-image" src="<%=request.getContextPath() %>/res/images/feather-12-black.svg" width="100">
            <h3>国内</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique.</p><a class="link" href="#">Text Link</a>
          </div>
          <div class="w-col w-col-3 col-spc"><img class="grid-image" src="<%=request.getContextPath() %>/res/images/feather-05-black.svg" width="100">
            <h3>海外</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique.</p><a class="link" href="#">Text Link</a>
          </div>
          <div class="w-col w-col-3 col-spc"><img class="grid-image" src="<%=request.getContextPath() %>/res/images/feather-24-black.svg" width="100">
            <h3>IT•経済</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique.</p><a class="link" href="#">Text Link</a>
          </div>
          <div class="w-col w-col-3 col-spc"><img class="grid-image" src="<%=request.getContextPath() %>/res/images/feather-66-black.svg" width="100">
            <h3>芸能</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique.</p><a class="link" href="#">Text Link</a>
          </div>
        </div>
        <div class="w-row">
          <div class="w-col w-col-3 col-spc"><img class="grid-image" src="<%=request.getContextPath() %>/res/images/feather-12-black.svg" width="100">
            <h3>スポーツ</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique.</p><a class="link" href="#">Text Link</a>
          </div>
          <div class="w-col w-col-3 col-spc"><img class="grid-image" src="<%=request.getContextPath() %>/res/images/feather-05-black.svg" width="100">
            <h3>映画</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique.</p><a class="link" href="#">Text Link</a>
          </div>
          <div class="w-col w-col-3 col-spc"><img class="grid-image" src="<%=request.getContextPath() %>/res/images/feather-24-black.svg" width="100">
            <h3>グルメ</h3>
            <p><%=itemList.get(0).getDescription() %></p><a class="link" href="#">Text Link</a>
          </div>
          <div class="w-col w-col-3 col-spc"><img class="grid-image" src="<%=request.getContextPath() %>/res/images/feather-66-black.svg" width="100">
            <h3>国内</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique.</p><a class="link" href="#">Text Link</a>
          </div>
        </div>
        <div class="w-row">
          <div class="w-col w-col-3 col-spc"><img class="grid-image" src="<%=request.getContextPath() %>/res/images/feather-12-black.svg" width="100">
            <h3>女子</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique.</p><a class="link" href="#">Text Link</a>
          </div>
          <div class="w-col w-col-3 col-spc"><img class="grid-image" src="<%=request.getContextPath() %>/res/images/feather-05-black.svg" width="100">
            <h3>トレンド</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique.</p><a class="link" href="#">Text Link</a>
          </div>
          <div class="w-col w-col-3 col-spc">
            <h3></h3>
            <p></p>
            <a class="link" href="#"></a>
          </div>
          <div class="w-col w-col-3 col-spc">
            <h3></h3>
            <p></p>
            <a class="link" href="#"></a>
          </div>
        </div>
      </div>
    </div>
    <div class="w-section section section-gray" id="contact">
      <div class="w-container">
        <div class="section-title-group">
          <h2 class="section-heading centered">Contact Form</h2>
          <div class="section-subheading center">This is some text inside of a div block.</div>
        </div>
        <div class="w-form form-wrapper squeezed">
          <form id="email-form" name="email-form" data-name="Email Form">
            <div class="w-row">
              <div class="w-col w-col-6 col-0">
                <input class="w-input form-field" id="name-7" type="text" placeholder="Name" name="name-7" data-name="Name 7">
              </div>
              <div class="w-col w-col-6 col-0">
                <input class="w-input form-field" id="email-9" type="email" placeholder="Email" name="email-9" data-name="Email 9" required="required">
              </div>
            </div>
            <textarea class="w-input form-field text-area" id="field-4" placeholder="Message" name="field-4" data-name="Field 4"></textarea>
            <input class="w-button button full-width" type="submit" value="Send Message" data-wait="Please wait...">
          </form>
          <div class="w-form-done success-message">
            <p class="p-form">Thank you! Your submission has been received!</p>
          </div>
          <div class="w-form-fail success-message">
            <p class="p-form">Oops! Something went wrong while submitting the form :(</p>
          </div>
        </div>
      </div>
    </div>
    <footer class="w-section footer center">
      <div class="w-container">
        <div class="footer-text">Copyright Incredible. Made in Webflow.</div>
      </div>
    </footer>
  </div>
  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <!--[if lte IE 9]><script src="https://cdnjs.cloudflare.com/ajax/libs/placeholders/3.0.2/placeholders.min.js"></script><![endif]-->
</body>
</html>