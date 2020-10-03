package model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateFeedRequest {

    @NotNull
    @NotEmpty
    private String src_url;

    public CreateFeedRequest(){

    }

    public String getSrcUrl(){
        return this.src_url;
    }

    public void setSrcUrl(String srcUrl){
        this.src_url = srcUrl;
    }
}
