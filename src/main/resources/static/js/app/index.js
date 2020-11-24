//p9-1

var main = { //js는 타입이 없어서 그냥 변수앞에 var를 쓴다
   init : function () { //function은 함수. 이 js파일엔 함수가 4개 있는것
       var _this = this;
       $('#btn-save').on('click', function () { // btn-save 앞에 #이 꼭 붙어야됨. id 이기 때문
           _this.save(); //btn-save가 click 됬을때, save 함수가 실행이 된다.
       })
   },
   save : function () {
       var data = {
           title : $("#title").val(),
           author : $("#author").val(),
           content : $("#content").val()
       }; //id인 title author content의 값을 title author content 변수에 넣고 그걸 하나로 묶어 data라는 객체가 갖게함

       $.ajax({
           type : 'POST', //메타데이터를 저장할때 얘의 타입이 'POST'라는 것
           url : '/api/v1/posts', //PostsApiController에 적힌 경로와 같다.
           dataType : 'json',
           contentType : 'application/json; charset=utf-8',
           data : JSON.stringify(data) //이 코드 블럭은 xml 형태 라고 생각하면 됨
       }).done(function () { //위에 것이 제대로 들어가게 되면 done 실행
           alert('글이 등록되었습니다.'); //화면에 조그만 윈도우즈 창이 뜨면서 '글이 등록되었습니다' 문구가 뜸
           window.location.href = '/'; //그 창에서 확인 누르면 다시 루트('/')로 돌아가라
       }).fail(function (error) { //위에 것이 제대로 안들어가면 에러 띄워라
           alert(JSON.stringify(error));
       })
   }
};

main.init(); //맨 처음에 이게 호출됨. main의 init이라는 function을 제일 먼저 호출함

//index.js가 수행되고 나면 제일 처음에 수행되는건 PostsApiController다.
