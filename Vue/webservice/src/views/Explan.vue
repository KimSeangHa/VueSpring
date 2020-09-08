<template>
    <div class="explan_wrap">
        <div class="explan_main">
            <h3>설명</h3>
            <p>내가 해야할 일들과 잊지 말아야할 것들을 자꾸 잊어버리고 못하시는 분들에게 유용한 기능입니다.</p>
            <p>해야할 일들을 메모장에 등록하시면, 텔레그램 스케줄러 봇이 해당일자에 등록한 메모를 09:00, 12:00, 18:00 하루에 총3번 메세지를 자동으로 보냅니다.</p>
            <p>그 외에도 텔레그램 봇을 통하여 내가 등록한 메모들을 텔레그램 대화방에서 받을 수 있습니다.</p>
            <br>
            <br>

            <h3>사용방법</h3>
            <p>회원가입 → 텔레그램 봇 그룹 대화창 초대 → 채팅방 ID 등록 → 메모 등록 → 봇이 보내주는 스케줄러 완료 ! </p>
            <br>
            <br>
            <p>1. 회원가입</p>
            <p><img :src="require(`@/assets/images/register.png`)" class="registerImg" /></p>
            <br>
            <br>
            <p>2. 텔레그램 봇 그룹 대화창 초대</p>
            <p>먼저 그룹원을 초대합니다. 먼저 스케줄러 봇을 초대하지 말아주세요.</p>
            <p><img :src="require(`@/assets/images/botInvite_1.png`)" class="registerImg" /></p>
            <br>
            <p>이후에 스케줄러 봇을 초대합니다.</p>
            <p><img :src="require(`@/assets/images/botInvite_2.png`)" class="registerImg" /></p>
            <br>
            <br>
            <p>3. 채팅방 ID 등록</p>
            <p>/help 명령어를 통하여 도움말을 받을 수 있습니다.</p>
            <p><img :src="require(`@/assets/images/makeChat_1.png`)" class="registerImg" /></p>
            <br>
            <p>/REG#id 명령어를 통하여 채팅방 아이디를 등록할 수 있습니다. 회원가입한 아이디를 입력해주세요.</p>
            <p>저는 "test" 라는 아이디로 회원가입을 했으므로 아이디를 test로 입력하였습니다.</p>
            <p><img :src="require(`@/assets/images/makeChat_2.png`)" class="registerImg" /></p>
            <br>
            <p>채팅방 아이디가 정상적으로 등록됬는지 확인하셔야 합니다. (홈페이지 → 로그인 → API 관리)</p>
            <p>아래와 같이 숫자가 입력되어 있으면 정상적으로 등록이 완료된 것입니다.</p>
            <p><img :src="require(`@/assets/images/makeChat_3.png`)" class="registerImg" /></p>
            <br>
            <br>
            <p>4. 메모 등록</p>
            <p>메모를 등록해주세요. (홈페이지 → 로그인 → MemoApp)</p>
            <p><img :src="require(`@/assets/images/makeMemo_1.png`)" class="registerImg" /></p>
            <br>
            <p>메모 등록이 완료되면 아래에 메모리스트에 출력됩니다.</p>
            <p><img :src="require(`@/assets/images/makeMemo_2.png`)" class="registerImg" /></p>
            <br>
            <br>
            <p>5. 봇이 보내주는 스케줄러 확인</p>
            <p>메모를 등록하면 스케줄러 봇이 초대되어 있는 방에 메모가 등록되었다는 영어 문구가 출력됩니다.</p>
            <p>등록된 메모를 확인하는 방법 : /20200909(해당날짜) 를 입력하시면 됩니다.</p>
            <p><img :src="require(`@/assets/images/bot_test_1.png`)" class="registerImg" /></p>
            <br>
            <p>날짜는 언제든지 상관이 없습니다. 내가 등록한 날짜를 입력해주시면 됩니다.</p>
            <p>입력한 날짜가 잘못되었으면 잘못되었다고 알려주며, 등록한 메모가 없을 시 없다고 알려주니 걱정하지마세요.</p>
            <p><img :src="require(`@/assets/images/bot_test_2.png`)" class="registerImg" /></p>
            <br>
            <br>
            <p>잘 사용하세요^^.</p>
        </div>
    </div>
</template>

<script>
export default {
    name: "Explan"
}
</script>

<style>
.explan_wrap {
    margin:0 auto;
    width: 70%;
}

.explan_main {
    margin-top: 60px;
}

.explan_main p {
    font-size:12px;
}

.registerImg {
    width: 500px;
}
</style>