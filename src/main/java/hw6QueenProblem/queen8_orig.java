package hw6QueenProblem;

class State implements Cloneable {
    static final int num_queen = 8;
    static final boolean simple_printout = true;
    static final int UNASSIGNED = -1;
    State() {
	pos = new int[num_queen];
    }
    public Object clone() {
	try {
	    State s = (State)super.clone();
	    s.pos = (int[])pos.clone();
	    return s;
	} catch (CloneNotSupportedException e) {
	    // Can't happen, but required.  Just ignore it
	    throw new InternalError(e.toString());
	}
    }
    void print() {
	for (int i = 0; i < num_queen; ++i) {
	    if (simple_printout) {
		System.out.print(pos[i]+" ");
	    } else {
		for (int j = 0; j < num_queen; ++j)
		    System.out.print(pos[i]==j?'Q':'.');
		System.out.println();
	    }
	}
	System.out.println();
    }

    int pos[];
}

public class queen8_orig {
    static final int NO_MORE_VAR = -1;
    static int total_expanded = 0;

    static int choose_var(State curr) {
	for (int i = 0; i < State.num_queen; ++i)
	    if (curr.pos[i] == State.UNASSIGNED)
		return i;
	return NO_MORE_VAR;
    }
    // A simple goal checker
    static boolean check_for_goal(State curr) {
	char found[] = new char[State.num_queen * 2];
	// Check for clashed columns
	for (int i = 0; i < State.num_queen; ++i)
	    found[i] = 0;
	for (int i = 0; i < State.num_queen; ++i) {
	    int p = curr.pos[i];
	    if (found[p] != 0)
		return false;
	    found[p] = 1;
	}
	// Check for clashed sum-diagonal
	for (int i = 0; i < 2*State.num_queen; ++i)
	    found[i] = 0;
	for (int i = 0; i < State.num_queen; ++i) {
	    int p = i+curr.pos[i];
	    if (found[p] != 0)
		return false;
	    found[p] = 1;
	}
	// Check for clashed difference-diagonal
	for (int i = 0; i < 2*State.num_queen; ++i)
	    found[i] = 0;
	for (int i = 0; i < State.num_queen; ++i) {
	    int p = State.num_queen-i+curr.pos[i];
	    if (found[p] != 0)
		return false;
	    found[p] = 1;
	}
	return true;
    }

    // No backtracking or forward checking now, so this function just
    // return true after making the needed assignment
    static boolean put_var(State s, int var, int value) {
	++total_expanded;
	s.pos[var] = value;
	return true;
    }

    // The core depth-first-search
    static State search(State curr) {
	int var = choose_var(curr);
	if (var == NO_MORE_VAR) { // Recursed to the bottom
	    if (!check_for_goal(curr))
		return null;
	    return curr;
	}
	State next_state = (State)curr.clone();
	for (int i = 0; i < State.num_queen; ++i) {
	    if (!put_var(next_state, var, i))
		continue;
	    State result = search(next_state);
	    if (result != null) // Recurse
		return result;
	}
	return null;
    }

    public static void main(String [] args) {
	State init = new State();
	for (int i = 0; i < State.num_queen; ++i)
	    init.pos[i] = State.UNASSIGNED;
	State result = search(init);
	if (result == null)
	    System.out.println("No solution");
	else
	    result.print();
	System.out.println("Expanded " + total_expanded + " states.");
    }

}
