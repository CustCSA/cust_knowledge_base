package study.mybatis;

public class Contact {
    private Integer id;
    private Integer userId;
    private Integer groupId;
    private String name;
    private String phone;

    public Contact() {}

    public Contact(Integer id, Integer userId, Integer groupId, String name, String phone) {
        this.id = id;
        this.userId = userId;
        this.groupId = groupId;
        this.name = name;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
