package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.user.User;

public class BoardResponse {

    @Data
    public static class DetailDTO {
        // 화면에서 안보여도 pk 는 하나 꼭 넣어줘야함. 이게 국룰
        private Integer boardId;
        private String title;
        private String content;
        private Integer userId;
        private String username;
        private Boolean isOwner;


        public DetailDTO(Board board, User sessionUser) {
            this.boardId = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();

            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();

            this.isOwner = false;

            if (sessionUser != null) {
                if (board.getUser().getId() == sessionUser.getId()) {
                    isOwner = true;
                }
            }
            
        }
    }


}
