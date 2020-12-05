//p9-1

var main = { //js는 타입이 없어서 그냥 변수앞에 var를 쓴다
   init : function () { //function은 함수. 이 js파일엔 함수가 4개 있는것
       var _this = this;
       $('#btn-save').on('click', function () { // btn-save 앞에 #이 꼭 붙어야됨. id 이기 때문
           _this.save(); //btn-save가 click 됬을때, save 함수가 실행이 된다.
       })
       $('#btn-update').on('click', function () { //p11-2 에서 추가 15분경
                  _this.update();
              })
       $('#btn-delete').on('click', function () { //p11-3 에서 추가 25:30
                                _this.delete();
       }); //원칙상 function 끝나는 부분에 세미콜론 해야함 p11-3 24:05

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
   }, //function을 이을때 컴마(,)로 이어야된다 p11-2 16:50 경

   update : function () { //p11-2 17분 경
          var data = {
              title : $("#title").val(),
              content : $("#content").val()
          }; //id인 title author content의 값을 title author content 변수에 넣고 그걸 하나로 묶어 data라는 객체가 갖게함

          var id = $("#id").val();

          $.ajax({
              type : 'PUT', //update는 Post Get Put Delete 중에 Put임
              url : '/api/v1/posts/'+id, //posts 뒤에 슬래시(/)를 붙이는 걸 잊지말자
                                         //put 타입으로 바꿔서 id를 url에 첨부
              dataType : 'json',
              contentType : 'application/json; charset=utf-8',
              data : JSON.stringify(data) //이 코드 블럭은 xml 형태 라고 생각하면 됨
          }).done(function () { //위에 것이 제대로 들어가게 되면 done 실행
              alert('글이 수정되었습니다.');
              window.location.href = '/'; //그 창에서 확인 누르면 다시 루트('/')로 돌아가라
          }).fail(function (error) { //위에 것이 제대로 안들어가면 에러 띄워라
              alert(JSON.stringify(error));
          })
      },

      update : delete () { //p11-3 24:18분 경
      // delete는 화면에 나타내야되는게 암것도 없음. 그냥 삭제됬다고 하면 됨
      // 그래서 var data 없어짐
        var id = $("#id").val(); //키값인 id는 있어야 해당하는 데이터를 삭제할수있으니 var id는 남김

        $.ajax({
            type : 'DELETE',
            url : '/api/v1/posts/'+id, //posts 뒤에 슬래시(/)를 붙이는 걸 잊지말자

            dataType : 'json',
            contentType : 'application/json; charset=utf-8'
            //여기도 data에 해당되는건 필요 없

        }).done(function () { //위에 것이 제대로 들어가게 되면 done 실행
            alert('글이 삭제되었습니다.');
            window.location.href = '/'; //그 창에서 확인 누르면 다시 루트(/')로 돌아가라
        }).fail(function (error) { //위에 것이 제대로 안들어가면 에러 띄워라
            alert(JSON.stringify(error));
        })
    }
};

main.init(); //맨 처음에 이게 호출됨. main의 init이라는 function을 제일 먼저 호출함

//index.js가 수행되고 나면 제일 처음에 수행되는건 PostsApiController다.
