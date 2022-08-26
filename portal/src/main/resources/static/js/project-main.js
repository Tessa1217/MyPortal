// 페이지 이동하는 함수
function movePage() {
	$(document).on("click", ".pageBtnContainer button", function(e) {
		e.stopPropagation();
		if ($(e.target).hasClass("prevBtn")) {
			prevPage($(e.target).data("idx"));
		}
		if ($(e.target).hasClass("nextBtn")) {
			nextPage($(e.target).data("idx"));
		}
	})
}

function nextPage(idx) {
	$($(".questionContainer")[idx]).addClass("hidePage");
	$($(".questionContainer")[idx + 1]).removeClass("hidePage");
}

function prevPage(idx) {
	$($(".questionContainer")[idx]).addClass("hidePage");
	$($(".questionContainer")[idx - 1]).removeClass("hidePage");
}

function fireSwal(icon, text) {
	return Swal.fire({
		icon: icon,
		text: text
	})	
}