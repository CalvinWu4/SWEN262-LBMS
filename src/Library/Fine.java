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

    public void addCost(long weeks){
        if(cost == 0){
            cost += INITIALFEE;
        }
        if((cost >= 10)){
            weeks = (weeks > 10 ? 10 : weeks);
            for(int i=0;i<weeks;i++) {
                cost += PROGRESSIVEFEE;
            }
        }
    }

    public Integer getCost(){
        return cost;
    }

}