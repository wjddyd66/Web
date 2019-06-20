var xhr;
var checkFirst = loopSend = false;
var lastKeyword = "";
//Timeout�� 1�ʷ� �ɾ���. ���� �ܾ� �ϼ� �� �˻�� ����� �˻��ϱ� ���ؼ� �̴�.
function sijak() {
    if (checkFirst == false) {
    	//1�ʵ� sendKeyword() ����
        setTimeout("sendKeyword()", 1000);
        loopSend = true;
    }
}

//Ajax�� Ȱ���Ͽ� suggest.jsp ���� ��û�� �ϴ� �Լ��̴�. �Ѱ��ִ� ���� keyword�̸� Post������� �����Ѵ�.
function sendKeyword() {
    if (loopSend == false)
        return;
    else {
        var keyWord = document.frm.keyword.value;
        //Ű���尡 hide �Լ��� �ҷ� �˻��� â �����
        if (keyWord === "") {
            lastKeyword = "";
            hide("suggest");
            //�˻�� �ִ� ��� suggest.jsp���� �� ��û
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
                        alert("��û����1" + xhr.status)
                    }
                }
            }
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.send(para);


        }
    }
}

//Ajax�� ����� �ް� ó���ϴ� �����̴�.
//Ajax���� ���� Data(�̸�)�� ��ũ�� �� �� Output-Suggest�� �������� �����̴�.
function process() {
    var data = xhr.responseText;
    var result = data.split("|");
    var tot = result[0];
    if (tot > 0) {
        var datas = result[1].split(",");
        var imsi = "";
        //������ �̸��� ��ũ �ɱ� ������ �̸��� func(�ڱ��̸�)�� �� �ִ�.
        for (var i = 0; i < datas.length; i++) {
            imsi += "<a href=\"javascript:func('" + datas[i] + "')\">" + datas[i] + "</a><br>";
        }
        //Output-Suggest�� ��� �����ֱ�
        var listView = document.getElementById("suggest").innerHTML = imsi;
    }

}

//�̸� Ŭ���� Output-Selected �� �� �ֱ�. Suggestâ �����
function func(reData) {
    frm.sel.value = reData;
    loopSend = checkFirst = false;
    lastKeyword = "";
    hide("suggest");

    frm.keyword.value="";

}

//Suggest â ����� �Լ�
function hide(ele) {
    var e = document.getElementById(ele);
    if (e) e.style.display = "none";
}

//Suggest â ���̰� �Ӽ� �ٲٴ� �Լ�
function show(ele) {
    var e = document.getElementById(ele);
    if (e) e.style.display = "";
}