<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     	
<!DOCTYPE html>
<html lang="en">
<head>
<title>지구력</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,500|Gaegu:700"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Caveat:wght@700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=East+Sea+Dokdo&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/open-iconic-bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/animate.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/owl.theme.default.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/magnific-popup.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/aos.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/ionicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/jquery.timepicker.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flaticon.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/icomoon.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/personal_css/join.css">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/earlyaccess/jejugothic.css">
<link rel="icon"
	href="${pageContext.request.contextPath}/resources/images/ficon.png">
<style>
body {
	font-family: 'Poppins', 'Jeju Gothic', serif;
}
</style>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	//모든 공백 체크 정규식
	var empJ = /\s/g;
	// 비밀번호 정규식
	var pwJ = /^[A-Za-z0-9]{4,12}$/;

	$(document).ready(function() {
		//비밀번호 입력
		$("#user_pw").blur(function() {
			if ($('#user_pw').val() == '') {
				$('#pw_check').text('비밀번호를 입력하세요.');
				$('#pw_check').css('color', 'red');
			} else if (pwJ.test($('#user_pw').val()) != true) {
				$('#pw_check').text('4~12자의 영문, 숫자만 사용 가능합니다.');
				$('#pw_check').css('color', 'red');
			} else if ($('#user_pw').val() != '') {
				$('#pw_check').css('display', 'none');
			}
		});

		//비밀번호 중복 확인
		$("#user_pw_chk").blur(function() {
			if ($('#user_pw_chk').val() == '') {
				$('#pw2_check').text('비밀번호를 입력하세요.');
				$('#pw2_check').css('color', 'red');
			} else if ($('#user_pw_chk').val() != $('#user_pw').val()) {
				$('#pw2_check').text('비밀번호가 다릅니다.');
				$('#pw2_check').css('color', 'red');
			} else if ($('#user_pw_chk').val() == $('#user_pw').val()) {
				$('#pw2_check').css('color', 'blue');
				$('#pw2_check').text('비밀번호가 일치합니다.');
			}
		});

		//이름 입력
		$("#user_name").blur(function() {
			if ($('#user_name').val() == '') {
				$('#name_check').text('이름을 입력하세요.');
				$('#name_check').css('color', 'red');
			} else if ($('#user_name').val() != '') {
				$('#name_check').css('display', 'none');
			}
		});

		//닉네임 중복확인
		$("#user_nick").blur(function() {
			if ($('#user_nick').val() == '') {
				$('#nick_check').text('닉네임을 입력하세요.');
				$('#nick_check').css('color', 'red');
			} else if ($('#user_nick').val() != '') {
				var user_nick = $('#user_nick').val();
				var param = {
					"user_nick" : user_nick
				};

				$.ajax({
					type : 'POST',
					data : JSON.stringify(param),
					url : 'nickCheckForUserUpdate.do',
					dateType : 'json',
					contentType : "application/json; charset=UTF-8",
					success : function(data) {
						if (data.check == true) {
							//사용할 수 있는 닉네임~!!
							$('#nick_check').text('사용가능한 닉네임 입니다.');
							$('#nick_check').css('color', 'blue');
							$("#usercheck").attr("disabled", false);
						} else {
							//중복닉네임~
							$('#nick_check').text('중복된 닉네임 입니다.');
							$('#nick_check').css('color', 'red');
							$("#usercheck").attr("disabled", true);
						}
					},
					error : function() {
						alert("AJAX 통신에러");
					}
				});//ajax///
			}//else if
		});//blur

		//전화번호 중복확인
		$("#user_phone").blur(function() {
			if ($('#user_phone').val() == '') {
				$('#phone_check').text('전화번호를 입력하세요.');
				$('#phone_check').css('color', 'red');
			} else if ($('#user_phone').val() != '') {
				var user_phone = $('#user_phone').val();
				var param = {
					"user_phone" : user_phone
				};

				$.ajax({
					type : 'POST',
					data : JSON.stringify(param),
					url : 'updatePhoneCheck.do',
					dateType : 'json',
					contentType : "application/json; charset=UTF-8",
					success : function(data) {
						if (data.check == true) {
							//사용할 수 있는 닉네임~!!
							$('#phone_check').text('사용가능한 전화번호 입니다.');
							$('#phone_check').css('color', 'blue');
							$("#usercheck").attr("disabled", false);
						} else {
							//중복닉네임~
							$('#phone_check').text('중복된 전화번호 입니다.');
							$('#phone_check').css('color', 'red');
							$("#usercheck").attr("disabled", true);
						}
					},
					error : function() {
						alert("AJAX 통신에러");
					}
				});//ajax///
			}//else if
		});//blur

		//주소 입력
		$("#user_addr").blur(function() {
			if ($('#user_addr').val() == '') {
				$('#addr_check').text('주소를 입력하세요.');
				$('#addr_check').css('color', 'red');
			} else if ($('#user_addr').val() != '') {
				$('#addr_check').css('display', 'none');
			}
		});

		//submit시에 빈칸확인
		$("#submit").on("click", function() {
			if ($("#user_id").val() == "") {
				alert("아이디를 입력해주세요.");
				$("#user_id").focus();
				return false;
			}
			if ($("#user_pw").val() == "") {
				alert("비밀번호를 입력해주세요.");
				$("#user_pw").focus();
				return false;
			}
			if ($('#user_pw_chk').val() != $('#user_pw').val()) {
				alert("비밀번호를 일치 시켜주세요.");
				$("#user_pw_chk").focus();
				return false;
			}
			if ($("#user_name").val() == "") {
				alert("성명을 입력해주세요.");
				$("#user_name").focus();
				return false;
			}
			if ($("#user_nick").val() == "") {
				alert("닉네임을 입력해주세요.");
				$("#user_nick").focus();
				return false;
			}
			if ($('#nick_check').text() == "중복된 닉네임 입니다.") {
				alert("중복된 닉네임 입니다.");
				$("#user_nick").focus();
				return false;
			}
			if ($('#phone_check').text() == "중복된 전화번호 입니다.") {
				alert("중복된 전화번호 입니다.");
				$("#user_phone").focus();
				return false;
			}
			if ($("#user_phone").val() == "") {
				alert("전화번호를 입력해주세요.");
				$("#user_phone").focus();
				return false;
			}
			if ($("#user_addr").val() == "") {
				alert("주소를 입력해주세요.");
				$("#user_addr").focus();
				return false;
			}
		});

	});
</script>

<script type="text/JavaScript"
	src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=76c0dbd7d9068bde24079e3cd4f90525&libraries=services"></script>
<script type="text/javascript">
	function openDaumZipAddress() {
		new daum.Postcode({
			oncomplete : function(data) {
				jQuery("#user_addr").val(data.address);
				address = data.address;
				console.log(data);

				console.log(address);
				//여기부터
				var geocoder = new daum.maps.services.Geocoder();
				// 주소로 좌표를 검색
				geocoder.addressSearch(address, function(result, status) {

					if (status == daum.maps.services.Status.OK) {
						jQuery("#x").val(result[0].x);
						jQuery("#y").val(result[0].y);

						console.log(result[0].x, result[0].y);
					}
					// 정상적으로 검색이 완료됐으면,
					/* if (status == daum.maps.services.Status.OK) {
					
					var coords = new daum.maps.LatLng(result[0].y, result[0].x);
					
					y = result[0].x;
					x = result[0].y;
					} */
				});
			}
		}).open();
	}
</script>

</head>
<body>

	<!----------------- START nav ----------------->
	<%@ include file="../header.jsp"%>
	<!-- END nav -->

	<div class="block-31" style="position: relative;">
		<div class="owl-carousel loop-block-31 ">
			<div class="block-30 block-30-sm item"
				style="background-image: url('${pageContext.request.contextPath}/resources/images/bg_3.jpg');"
				data-stellar-background-ratio="0.5">
				<div class="container">
					<div class="row align-items-center justify-content-center">
						<div class="col-md-7 text-center">
							<h2 class="heading">회원정보 수정</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="site-section">
		<div class="container" style="text-align: center;">
			<div class="row block-9">
				<div class="col-md-6 pr-md-5" style="margin: 0 auto;">
					<form action="user_updateRes.do" method="POST"
						enctype="multipart/form-data" id="usercheck">
						<div class="form-group joinbox">
							<input type="hidden" class="form-control px-3 py-3 join_input"
								placeholder="ID" style="width: 500px;" name="user_id"
								id="user_id" value="${user.getUser_id() }">
							<div class="check_text" id="id_check"></div>
						</div>
						<div class="form-group joinbox">
							<input type="password" class="form-control px-3 py-3 join_input"
								placeholder="Password" style="width: 500px;" name="user_pw"
								id="user_pw" value="${user.getUser_pw() }">
							<div class="check_text" id="pw_check"></div>
						</div>
						<div class="form-group joinbox">
							<input type="password" class="form-control px-3 py-3 join_input"
								placeholder="Password 확인" style="width: 500px;"
								name="user_pw_chk" id="user_pw_chk"
								value="${user.getUser_pw() }">
							<div class="check_text" id="pw2_check"></div>
						</div>
						<div class="form-group joinbox">
							<input type="text" class="form-control px-3 py-3 join_input"
								placeholder="Name" style="width: 500px;" name="user_name"
								id="user_name" value="${user.getUser_name() }">
							<div class="check_text" id="name_check"></div>
						</div>
						<div class="form-group joinbox">
							<input type="text" class="form-control px-3 py-3 join_input"
								placeholder="NickName" style="width: 500px;" name="user_nick"
								id="user_nick" value="${user.getUser_nick() }">
							<div class="check_text" id="nick_check"></div>
						</div>
						<c:if test="${empty snsCheck }">
							<div class="form-group joinbox">
								<input type="tel" class="form-control px-3 py-3 join_input"
									placeholder="Phone ('-'없이 번호만 입력해주세요)" style="width: 500px;"
									name="user_phone" id="user_phone"
									value="${user.getUser_phone() }">
								<div class="check_text" id="phone_check"></div>
							</div>
											
						</c:if>
						<div class="form-group joinbox">
							<input type="text" class="form-control px-3 py-3 join_input"
								placeholder="Address" style="width: 400px;" name="user_addr"
								id="user_addr" value="${user.getUser_addr() }"> <input
								type="button" value="주소 찾기" onClick="openDaumZipAddress();"
								class="form-control px-3 py-3 join_input"
								style="width: 96px; cursor: pointer; background-color: #999999 !important; color: #fff !important;" />
							<div class="check_text" id="addr_check"></div>
						</div>
						<div class="form-group joinbox">
							<input type="file" class="form-control px-3 py-3 join_input"
								placeholder="File"
								style="width: 500px; border: none; padding-left: 0px !important;"
								name="file" id="file">
						</div>
						<div class="form-group">
							<input type="submit" value="수정"
								class="btn btn-success py-3 px-5 btn_success"
								style="width: 500px;" id="submit">
						</div>
						<div class="form-group">
							<input type="button" value="취소" class="btn py-3 px-5 cancelbtn"
								style="width: 500px;" onclick="location.href='myPage.do'">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


	<!-- START footer -->
	<%@ include file="../footer.jsp"%>
	<!-- END footer -->

	<!-- loader -->
	<div id="ftco-loader" class="show fullscreen">
		<svg class="circular" width="48px" height="48px">
			<circle class="path-bg" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke="#eeeeee" />
			<circle class="path" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke-miterlimit="10" stroke="#F96D00" /></svg>
	</div>


	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery-migrate-3.0.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.easing.1.3.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.waypoints.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.stellar.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.js"></script>

	<script src="${pageContext.request.contextPath}/resources/js/aos.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.animateNumber.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/google-map.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

</body>
</html>