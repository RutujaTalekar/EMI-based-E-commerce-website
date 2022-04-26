<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
.Platinumfaq {
	width: 60%;
}

.Platinumsection {
	background-color: rgb(194, 193, 193);
	color: black;
	cursor: pointer;
	padding: 18px;
	width: 100%;
	border: none;
	text-align: left;
	outline: none;
	font-size: 15px;
	transition: 0.4s;
	margin-left: 15%;
	margin-bottom: 2%;
	border-radius: 10px;
}

.active, .Platinumsection:hover {
	background-color: #006666;
}

.panel {
	display: none;
	background-color: white;
	overflow: hidden;
	margin-left: 20%;
}

#platinumhead {
	margin-left: 30%;
	color: #006666;
}
</style>
</head>
<body>
	<%@include file="header.jsp"%>
	<h2 id="platinumhead">Platinum Card FAQ's</h2>

	<div class="Platinumfaq">
		<button class="Platinumsection">1) Why should I select a
			Platinum EMI Card?</button>
		<div class="panel">
			<p>EMI Cards symbolize the following guiding principles: 1)
				Simplicity 2) Transparency 3) Relevance</p>
		</div>

		<button class="Platinumsection">2) Whom has the Platinum EMI
			Card been designed for?</button>
		<div class="panel">
			<p>The Platinum EMI Card has been specifically designed for
				salaried segment. We have kept in mind the major spend areas for
				you, such that we give you the most relevant benefits and you can
				derive the maximum out of it.</p>
		</div>

		<button class="Platinumsection">3) What type of Card is
			Platinum Card ?</button>
		<div class="panel">
			<p>The Platinum Card is a VISA Platinum Card.</p>
		</div>

		<button class="Platinumsection">4) What is the Annual and
			Joining Fee for this Card?</button>
		<div class="panel">
			<p>There is no annual fee for EMI card but the joining fee
				Platinum EMI card is 1000 (Applicable only for Platinum EMI card
				after 29 October 2019).</p>
		</div>

		<button class="Platinumsection">5) Is there a special service
			offered while canceling the tickets?</button>
		<div class="panel">
			<p>No there is no special service offered. The surcharge will be
				automatically reversed if you cancel the tickets.</p>
		</div>

		<button class="Platinumsection">6) What is the minimum/
			maximum transaction amount for a single swipe?</button>
		<div class="panel">
			<p>The minimum is Rs. 800 and the maximum is Rs. 8000.</p>
		</div>

		<button class="Platinumsection">7) How is it different from
			the regular service of cash withdrawal at the ATMs?</button>
		<div class="panel">
			<p>Cash withdrawal at an ATM is only possible if you remember
				your Credit Card PIN, incase you have forgotten the same please walk
				into our branch and withdraw cash from your credit card by producing
				relevant ID Proofs.</p>
		</div>

		<button class="Platinumsection">8) What are the ID proofs
			that can be accepted for the same?</button>
		<div class="panel">
			<p>The following can be used - Driving License, Passport, PAN
				card, Voters ID.</p>
		</div>
	</div>

	<script type="text/javascript">
		var acc = document.getElementsByClassName("Platinumsection");
		var i;

		for (i = 0; i < acc.length; i++) {
			acc[i].addEventListener("click", function() {
				this.classList.toggle("active");
				var panel = this.nextElementSibling;
				if (panel.style.display === "block") {
					panel.style.display = "none";
				} else {
					panel.style.display = "block";
				}
			});
		}
	</script>

</body>
</html>