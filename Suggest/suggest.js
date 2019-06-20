var xhr;
var checkFirst = loopSend = false;
var lastKeyword = "";
//Timeout을 1초로 걸었다. 일정 단어 완성 뒤 검색어를 만들어 검색하기 위해서 이다.
function sijak() {
    if (checkFirst == false) {
    	//1초뒤 sendKeyword() 수행
        setTimeout("sendKeyword()", 1000);
        loopSend = true;
    }
}

//Ajax를 활용하여 suggest.jsp 에게 요청을 하는 함수이다. 넘겨주는 값은 keyword이며 Post방식으로 전송한다.
function sendKeyword() {
    if (loopSend == false)
        return;
    else {
        var keyWord = document.frm.keyword.value;
        //키워드가 hide 함수를 불러 검색어 창 숨기기
        if (keyWord === "") {
            lastKeyword = "";
            hide("suggest");
            //검색어가 있는 경우 suggest.jsp에게 값 요청
        } else if (keyWord !== lastKeyword) {
            lastKeyword = keyWord;

            var para = "keyword=" + keyWord;
            xhr = new XMLHttpRequest();
            xhr.open("post", "suggest.jsp", true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200) {
                        process();
                    } else {
                        alert("요청실패1" + xhr.status)
                    }
                }
            }
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.send(para);


        }
    }
}

//Ajax의 결과를 받고 처리하는 공간이다.
//Ajax에게 받은 Data(이름)에 링크를 건 뒤 Output-Suggest에 보여지는 형식이다.
function process() {
    var data = xhr.responseText;
    var result = data.split("|");
    var tot = result[0];
    if (tot > 0) {
        var datas = result[1].split(",");
        var imsi = "";
        //각각의 이름에 링크 걸기 각각의 이름은 func(자기이름)이 들어가 있다.
        for (var i = 0; i < datas.length; i++) {
            imsi += "<a href=\"javascript:func('" + datas[i] + "')\">" + datas[i] + "</a><br>";
        }
        //Output-Suggest에 결과 보여주기
        var listView = document.getElementById("suggest").innerHTML = imsi;
    }

}

//이름 클릭시 Output-Selected 에 값 넣기. Suggest창 숨기기
function func(reData) {
    frm.sel.value = reData;
    loopSend = checkFirst = false;
    lastKeyword = "";
    hide("suggest");

    frm.keyword.value="";

}

//Suggest 창 숨기는 함수
function hide(ele) {
    var e = document.getElementById(ele);
    if (e) e.style.display = "none";
}

//Suggest 창 보이게 속성 바꾸는 함수
function show(ele) {
    var e = document.getElementById(ele);
    if (e) e.style.display = "";
}