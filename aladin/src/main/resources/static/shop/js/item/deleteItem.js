function deleteItem(){

     var id = $('#id').val();
     console.log(id);

     var itemName = $('#itemName').val();
     var orderItemName = $('#orderItemName').val();

     if (itemName == orderItemName) {
        alert("주문요청을 받은 상품을 삭제할 수 없습니다.");
        window.location.replace('/sales');
     } else {
        $.ajax({
            url : "/api/items/" + id,
            type : "delete",
            dataType : 'json',
            contentType : false,
            processData : false,
            success : function(data) {
                alert("상품이 삭제되었습니다.");
                window.location.replace('/sales');
            },
            error : function(error){
                alert("상품 삭제 중 오류가 발생했습니다.");
                window.location.replace('/sales');
            }
        });

     }


}