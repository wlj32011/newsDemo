package cn.boc.newsdemo;

import java.util.List;

/**
 * Created by wanglj on 16/7/20.
 */

public class News {
    /**
     * status_code : 200
     * status_msg : 获取文章列表成功
     * data : [{"id":1,"title":"标题","content":"爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了","image_url":"http://photocdn.sohu.com/20160720/Img460079789.jpg"},{"id":2,"title":"标题","content":"爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了","image_url":"http://photocdn.sohu.com/20160720/Img460079789.jpg"},{"id":3,"title":"标题","content":"爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了","image_url":"http://photocdn.sohu.com/20160720/Img460079789.jpg"},{"id":4,"title":"标题","content":"爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了","image_url":"http://photocdn.sohu.com/20160720/Img460079789.jpg"}]
     */

    private int status_code;
    private String status_msg;
    /**
     * id : 1
     * title : 标题
     * content : 爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了爱上空间的发挥了
     * image_url : http://photocdn.sohu.com/20160720/Img460079789.jpg
     */

    private List<DataEntity> data;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getStatus_msg() {
        return status_msg;
    }

    public void setStatus_msg(String status_msg) {
        this.status_msg = status_msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        private int id;
        private String title;
        private String content;
        private String image_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }
}
