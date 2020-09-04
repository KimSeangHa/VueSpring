<template>
    <div class="diagram_wrap">
        <img :src="require(`@/assets/images/diagram.png`)" class="diagramImg" />
        <div class="sub_text_form">
            <table class="sub_text_table">
                <tr>
                    <th>1. 개발환경</th>
                </tr>
                <tr>
                    <td class="sub">OS : </td>
                    <td class="cont">Windows10 Pro</td>
                </tr>
                <tr>
                    <td class="sub">Vue.js : </td>
                    <td class="cont">4.4.6</td>
                </tr>
                <tr>
                    <td class="sub">Spring : </td>
                    <td class="cont">4.3.4 RELEASE</td>
                </tr>
                <tr>
                    <td class="sub">DB : </td>
                    <td class="cont">MySQL 5.7</td>
                </tr>
                <tr>
                    <td class="sub">JAVA : </td>
                    <td class="cont">1.8</td>
                </tr>
                <tr>
                    <td class="sub">GitHub : </td>
                    <td class="cont"><a href="https://github.com/KimSeangHa/VueSpring">https://github.com/KimSeangHa/VueSpring</a></td>
                </tr>
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <th colspan="2">2. 주요기능</th>
                </tr>
                <tr>
                    <td colspan="2">메모장에 등록한 내용들을 텔레그램 봇을 통하여 그룹 대화창에서 받을 수 있습니다.</td>
                </tr>
                <tr>
                    <td colspan="2">텔레그램 봇과 대화하여 원하는 일자에 대한 메모를 받을 수 있습니다.</td>
                </tr>
                <tr>
                    <td colspan="2">Windows Batch Script를 통하여 하루에 총 3번(09:00, 13:00, 18:00) </td>
                </tr>
                <tr>
                    <td colspan="2">DB를 조회하여 Get 방식으로 Telegram Bot API를 통하여 메시지를 전송합니다. </td>
                </tr>
                <tr>
                    <td colspan="2">세부 설명은 "설명" 탭에서 하도록 하겠습니다.</td>
                </tr>
            </table>


        </div>
    </div>
</template>

<script>
export default {
    name: "Diagram"
}
</script>

<style>
.diagram_wrap {
    margin:0 auto;
    width: 70%;
}

.diagramImg {
    margin-top:50px;
    margin-bottom: 50px;
    width: 900px;
}

@media ( min-width: 1300px ) {
    .diagramImg {
        width: 1200px;
    }
}

@media (min-width: 769px) and (max-width: 480px) {
    .diagramImg {
        width: 900px;
    }
}

@media screen and (max-width: 768px) {
    .diagramImg {
        width: 300px;
    }
}

.sub_text_form {
    margin-top: 20px;
    text-align: center;
}

.sub_text_table {
    margin-left: 150px;
    text-align: left;
    font-size: 14px;
    margin-bottom: 100px;
}

.sub_text_table tr {
    height: 35px;
}

.sub_text_table .sub {
    width: 100px;
}

</style>