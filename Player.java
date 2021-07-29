import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public abstract class Player extends HeuristicScore {
  protected final double PLAYER1_WIN_SCORE = 1;
  protected final double PLAYER2_WIN_SCORE = -1;
  protected final double DRAW_SCORE = 0;

  long moveCounter;
  // int currentDepth;

  protected int maxDepth;

  public Player(int maxDepth) {
    this.maxDepth = maxDepth;
  }

  protected abstract double treeEval(Board board, PlayerEnum nextPlayer);

  public Board selectMove(Board board, PlayerEnum currentPlayer) {
    moveCounter = 0;
    // currentDepth = 0;

    ArrayList<Board> moves = board.ExpandChildren(currentPlayer);

    Double[] hScores = evaluateMoves(currentPlayer, moves);
    List<Double> scoresList = new ArrayList<Double>();
    scoresList = Arrays.asList(hScores);

    /*double bestScore;
    if(currentPlayer == PlayerEnum.PLAYER1) {
      bestScore = Collections.max(scoresList);
    } else {
      bestScore = Collections.min(scoresList);
    }*/

    double bestScore = Collections.max(scoresList);

    for(int i=0; i<scoresList.size(); i++){
      System.out.println(i + "th score: " +scoresList.get(i));
    }
    System.out.println("score: " + bestScore);
    int bestIndex = scoresList.indexOf(bestScore);
    Board moveToPlay = moves.get(bestIndex);

    return moveToPlay;
  }

  protected Double[] evaluateMoves(PlayerEnum currentPlayer, ArrayList<Board> moves) {
    Double[] scores = new Double[moves.size()];

    moves.forEach((move) -> {
      // currentDepth = 0;
      double score = treeEval(move, currentPlayer);
      scores[moves.indexOf(move)] = score;
    });
    return scores;
  }

  protected PlayerEnum nextPlayer(PlayerEnum lastPlayer) {
		PlayerEnum nextPlayer;
		if (lastPlayer == PlayerEnum.PLAYER1)
			nextPlayer = PlayerEnum.PLAYER2;
		else
			nextPlayer = PlayerEnum.PLAYER1;
		return nextPlayer;
	}

}
