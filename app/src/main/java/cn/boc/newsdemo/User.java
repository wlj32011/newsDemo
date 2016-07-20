package cn.boc.newsdemo;

/**
 * Created by wanglj on 16/7/20.
 */

public class User {


    /**
     * status_code : 200
     * status_msg : 登录成功
     * data : {"id":1,"username":"admin","password":"123456","gender":"男"}
     */

    private int status_code;
    private String status_msg;
    /**
     * id : 1
     * username : admin
     * password : 123456
     * gender : 男
     */

    private DataEntity data;

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

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
        private int id;
        private String username;
        private String password;
        private String gender;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "DataEntity{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", gender='" + gender + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "status_code=" + status_code +
                ", status_msg='" + status_msg + '\'' +
                ", data=" + data.toString() +
                '}';
    }
}
