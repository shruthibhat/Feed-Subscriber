package model;

public class Feed {
    String id;
    String title;
    String rss_url;
    String src_url;

    public String getId(){
        return this.id;
    }

     public String getTitle(){
         return this.title;
     }

     public String getRssUrl(){
         return this.rss_url;
     }

     public String getSrcUrl(){
         return this.src_url;
     }

     public void setId(String id){
        this.id = id;
     }

     public void setTitle(String title){
         this.title = title;
     }

     public void setRssUrl(String rssUrl){
         this.rss_url = rssUrl;
     }

     public void setSrcUrl(String srcUrl){
         this.src_url = srcUrl;
     }
}
