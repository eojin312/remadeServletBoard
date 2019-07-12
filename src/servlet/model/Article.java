package servlet.model;

public class Article {
    private Long atNo;
    private String writer;
    private String title;
    private String atTag;
    private String contents;
    private String createTime;
    private long readCount;
    private long recommendCount;

    public Article(Long atNo, String writer, String title, String atTag, String createTime) {
        this.atNo = atNo;
        this.writer = writer;
        this.title = title;
        this.atTag = atTag;
        this.createTime = createTime;
    }


    public Article(Long atNo, String writer, String title, String contents, String createTime, long readCount, long recommendCount) {
        this.atNo = atNo;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createTime = createTime;
        this.readCount = readCount;
        this.recommendCount = recommendCount;
    }
    public Article(Long atNo, String writer, String title, String atTag, String contents, String createTime) {
        this.atNo = atNo;
        this.writer = writer;
        this.title = title;
        this.atTag = atTag;
        this.contents = contents;
        this.createTime = createTime;
    }

    public Article(String title, String writer, long atNo, String createTime, String atTag, String contents) {
        this.atNo = atNo;
        this.writer = writer;
        this.title = title;
        this.atTag = atTag;
        this.contents = contents;
        this.createTime = createTime;
    }

    public Long getAtNo() {
        return atNo;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getAtTag() {return atTag; }

    public String getContents() { return contents.replaceAll("\r\n", "<br>"); }

    public String getCreateTime() {
        return createTime;
    }

    public long getReadCount() {
        return readCount;
    }

    public long getRecommendCount() {
        return recommendCount;
    }
}
