// 탭 형식으로 보이기
function showPage() {
	let icons = $("input[name=tabs]");
	$(icons).each((index, val) => {
		$(val).on("click", function(e) {
			$(icons).siblings("label").removeClass("active");
			if ($(val).is(":checked")) {
				$(val).next().addClass("active");
				let page = $(".paging");
				$(page).each((idx, val) => {
					$(val).addClass('hidePage');
				})
				$(page[index]).removeClass("hidePage");
			}
		})
	})
}

// 페이지 이동하는 함수
function movePage() {
	$(document).on("click", ".pageBtnContainer button", function(e) {
		e.stopPropagation();
		if ($(e.target).hasClass("prevBtn")) {
			prevPage($(e.target).data("idx"), ".questionContainer");
		}
		if ($(e.target).hasClass("nextBtn")) {
			nextPage($(e.target).data("idx"), ".questionContainer");
		}
	})
}

// a 태그 tr 태그로부터 이벤트 전파 막기
function stopFromMoving() {
	const a = document.querySelectorAll("table a");
	a.forEach((val) => {
		val.addEventListener("click", (e) => {
			e.stopPropagation();
		})
	})
}

function moveOmrPage() {
	$(document).on("click", ".omrBtnContainer button", function(e) {
		e.stopPropagation();
		if ($(e.target).hasClass("prevBtn")) {
			prevPage($(e.target).data("idx"), ".omrCard");
		}
		if ($(e.target).hasClass("nextBtn")) {
			nextPage($(e.target).data("idx"), ".omrCard");
		}
	})
}

// 이후 페이지로 이동
function nextPage(idx, target) {
	$(target).eq(idx).addClass("hidePage");
	$(target).eq(idx + 1).removeClass("hidePage");
}

// 이전 페이지로 이동
function prevPage(idx, target) {
	$(target).eq(idx).addClass("hidePage");
	$(target).eq(idx - 1).removeClass("hidePage");
}

// 에러창 (icon, text만 존재)
function fireSwal(icon, text) {
	return Swal.fire({
		icon: icon,
		text: text
	})
}

// 다양한 옵션 넣기 가능한 에러 창
function customFireSwal(msg) {
	return Swal.fire({
		icon: msg.icon,
		text: msg.text,
		showDenyButton: msg.deny,
		showCancelButton: msg.cancel,
		confirmButtonText: msg.confirmText,
		denyButtonText: msg.denyText
	})
}

// 주차별 날짜 자동 입력(중복 코드, 추후 취합 필요)
function enterDate(startname, endname) {
	$(document).on("change", "select[name=weekCode]", function(e) {
		let startDate = $($(e.target).children(":selected")).data("start");
		let endDate = $($(e.target).children(":selected")).data("end");
		$("input[name=" + startname + "]").val(startDate);
		$("input[name=" + endname + "]").val(endDate);
	})
}

// 수정 페이지와 상세 보기 페이지 변경
function updatePage() {
	$(document).on("click", "#changeBtn", function() {
		let views = $("#main").children();
		$(views).toggleClass("hidePage");
	})
}

// 수정 에디터
function updateEditor() {
	tinymce.init({
		selector: '#updateEditor',
		submit_patch: false,
		setup: function(editor) {
			editor.on('change', function() {
				editor.save();
			})
		}
	})
}

function initiateEditor(editorName) {
	tinymce.init({
		selector: '#' + editorName,
		submit_path: false,
		setup: function(editor) {
			editor.on('change', function() {
				editor.save();
			})
		}
	})
}

// Criteria Data 만들기
function makeCriData(pageNum, amount, type, keyword) {
	return data = {
		pageNum: pageNum,
		amount: amount,
		type: (type != null) ? type : '',
		keyword: (keyword != null) ? keyword : ''
	}
}

// Full Calendar 하루 더하기
Date.prototype.addDay = function() {
	let date = new Date(this.valueOf());
	date.setDate(date.getDate() + 1);
	return date;
}

// 캘린더 생성
function monthlyCalendar() {
	var calendarEl = document.getElementById('calendar');
	var calendar = new FullCalendar.Calendar(calendarEl, {
		editable: false,
		initialView: 'dayGridMonth',
		displayEventTime: false,
		headerToolbar: {
			left: '',
			center: '',
			right: ''
		}
	});
	calendar.render();

	getCalendar().done(function(data) {
		data.forEach((val) => {
			let event = {
				title: val.scheduleContent,
				start: val.scheduleStartDate,
				end: val.scheduleStartDate != val.scheduleEndDate ? new Date(val.scheduleEndDate).addDay() : val.scheduleStartDate,
				backgroundColor: getColor(val.detailCode)
			}
			event.borderColor = event.backgroundColor;
			calendar.addEvent(event);
		})
	})
}

// Full calendar 그룹별 색상 함수
function getColor(code) {
	if (code.startsWith('REG')) {
		return '#6666cc';
	} else if (code.startsWith('HOL')) {
		return '#ef4d27';
	} else if (code.startsWith('PROF')) {
		return '#9966cc';
	} else if (code.startsWith('BPLAN')) {
		return '#33cc99';
	} else if (code.startsWith('STUD')) {
		return '#6666ff';
	} else if (code.startsWith('SEME')) {
		return '#a634a4';
	}
}

// 공지사항 목록 
function getNotice(uri) {
	$.ajax({
		method: 'POST',
		url: '/' + uri + '/getNotice',
		success: function(fragment) {
			$("#noticeFragment").replaceWith(fragment);
		}
	})
}

// 페이지 이동 
function movePagination(uri, queryString) {
	location.href = uri + queryString;
}

// 수정 취소
function cancelUpdate() {
	$(document).on("click", "#check", function() {
		fireSwal('info', '수정을 취소하시겠습니까?')
			.then((result) => {
				$("#updateView").addClass("hidePage");
				$("#detailView").removeClass("hidePage");
			})
	})
}

// 페이지 이동 (path variable)
function changePage(uri, select) {
	location.href = uri + '/eclass/' + select.value;
}

// 모달 창 열기
function modalOpen(modalName) {
	$("#" + modalName).show();
}

// 모달 창 닫기
function modalClose(modalName) {
	$("#" + modalName).hide();
}