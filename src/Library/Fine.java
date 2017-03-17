package Library;

/**
 * Created by Anthony Perez on 3/10/17.
 */
public class Fine {
    private static final Integer INITIALFEE = 10;
    private static final Integer PROGRESSIVEFEE = 2;
    private Integer cost;


    public Fine(){
        this.cost = 0;
    }

    public Integer getCost(){
        return cost;
    }

    public void resetCost(){
        this.cost = 0;
    }

    public void addCost(Long weeks){
        if(this.getCost() == 0){
            this.cost += INITIALFEE;
        }
        if((this.getCost() >= 10)){
            weeks = (weeks > 10 ? 10 : weeks);
            for(int i=0;i<weeks;i++) {
                this.cost += PROGRESSIVEFEE;
            }
        }
    }
}