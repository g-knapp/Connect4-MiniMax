import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MiniMax extends Player{

  String id;
  // int currentDepth;

  public MiniMax(int maxDepth, String stratID) {
    super(maxDepth);
    this.id = stratID;
  }

  public double MiniMaxSearch(Board currentBoard, boolean maxTurn, PlayerEnum player, int currentDepth){ // agent input 
    //return column, move = number of moves down tree, maxmin is max if 1, min if 0
    // System.out.println("here");
    moveCounter++;
    // currentDepth++;

    // System.out.println("depth: " + currentDepth);

    GameStatusEnum gameStatus = currentBoard.gameStatus();

    // System.out.println(gameStatus);
    if(gameStatus != GameStatusEnum.ONGOING) {
      return CalculateScore(currentBoard.board, id, player);
    }

    if(currentDepth >= maxDepth){
      // System.out.println("3s: ");
      double score = CalculateScore(currentBoard.board, id, player); //column, then score (0 since doesn't matter)
      return score;
    }

    PlayerEnum nextPlayer = nextPlayer(player);
    ArrayList<Board> Children = currentBoard.ExpandChildren(nextPlayer);
    // Children.get(0).printBoard();
    // Children.forEach((move) -> {
    //   move.printBoard();
    // });
    List<Double> scores = new ArrayList<Double>();
    for (Board move : Children) {
      double score = MiniMaxSearch(move, !maxTurn, nextPlayer, currentDepth+1);
      scores.add(score);
    }
    if(!maxTurn){ //max = 1
      return Collections.max(scores);
    } else {
      return Collections.min(scores);
    }
  }

  @Override
  protected double treeEval(Board board, PlayerEnum nextPlayer) {
    // currentDepth = 0;
    return MiniMaxSearch(board, true, nextPlayer, 1);
  }
}
