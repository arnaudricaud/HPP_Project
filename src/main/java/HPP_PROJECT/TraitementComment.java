package HPP_PROJECT;

import java.util.ArrayList;

public class TraitementComment {

    private ArrayList<Comment> listComment = new ArrayList<Comment>();
    private Comment commentActu;
    private ReaderComment reader;

    public TraitementComment(){
        reader = ReaderComment.getInstance();
    }
}
