function editItemSubmit(){

    var form = $("#editItemForm")[0];
    var formData = new FormData(form);

    //data json 으로 저장 -> // UpdateItemRequest 정보 저장
    var data = {
        "itemName" : $.trim($("#itemName").val()),
        "price" : $.trim($("#price").val()),
        "quantity" : $.trim($("#quantity").val()),
    }
    formData.append("updateItemRequest", new Blob([JSON.stringify(data)] , {type: "application/json"}));     //updateItemRequest 이름으로 게시글 내용 저장
    var id = $('#id').val();

    // 이미지 파일을 담을 배열 (버튼을 눌렀을 때 서버에 전송할 데이터)
    var inputFileList = new Array();

    // 파일 선택 이벤트
    $('input[name=images]').on('change', function(e) {
    　　var files = e.target.files;
    　　var filesArr = Array.prototype.slice.call(files);

        // 이미지 파일을 배열에 담는다.
        filesArr.forEach(function(f){
            inputFileList.push(f);
        });

        // 이미지 파일을 배열에 담는다.
    　　filesArr.forEach(function(f) {
    　　　　inputFileList.push(f);
    　　});
    });


    // Check if image files were selected
    if (inputFileList.length > 0) {
        // Add image files to the FormData
        for (let i = 0; i < inputFileList.length; i++) {
            formData.append("images", inputFileList[i]);
        }
    }


    // 배열에서 이미지들을 꺼내 폼 객체에 담는다.
    for (let i = 0; i < inputFileList.length; i++) {
        formData.append("images", inputFileList[i]);
    }

    $.ajax({
                url : "/api/items/" + id,
                type : "patch",
               // enctype:"multipart/form-data", // 이미지 업로드 필수 파라미터
                data : formData,
                dataType : 'json',
                contentType : false, // 이미지 업로드 필수 파라미터
                processData : false, // 이미지 업로드 필수 파라미터
                success : function(data) {
                    alert("상품이 수정되었습니다.");
                    window.location.replace('/items'); // 메인 홈 창으로 이동

                },
                error : function(error){
                    alert(JSON.stringify(error));
                }
    });
}


function deleteImg(imgId) {
    var id = $('#id').val();
    var imgId = $('#imgId').val();
    var isMainImg = $('#isMainImg').val();

    console.log("imgId=" + imgId);

    if (data.isMainImg == 'NO') {
        $.aja({
            url: "/items/" + id + "/img/" + imgId,
            type: "DELETE",
            dataType: 'json',
            success: function (data) {
                alert("이미지가 삭제되었습니다.");
            },
            error: function (error) {
                console.error("Error deleting image: " + JSON.stringify(error));
                alert("이미지를 삭제할 수 없습니다.");
            }
        });
    } else if (data.isMainImg == 'YES') {
        alert("대표 이미지를 삭제할 수 없습니다.");
    }


}