'''
https://www.codewars.com/kata/path-finder-number-1-can-you-reach-the-exit/python
Breadth First, non-recursive traversal.
unvisited_spaces is a 2D boolean grid used tobytes prevent 
backtracking. Far faster than a searchable list of coords pairs.
'''
def path_finder(maze):
    maze = maze.split("\n")
    return solvable(maze)

def solvable(maze):
    global unvisited_spaces, moves, N
    N = len(maze)
    unvisited_spaces = [[True for _ in range(N)] for _ in range(N)]
    moves = [(0,0)]
    next_move_i = 0
    while(True):        
        row, col = moves[next_move_i]
        next_move_i += 1
        get_next_steps(maze, row, col)
        if(row == N-1 and col == N-1):
            return True
            
        if(next_move_i >= len(moves)):
            return False

def get_next_steps(maze, row, col):
    global unvisited_spaces, moves, N
    for rc in [(-1,0),(1,0),(0,-1),(0,1)]:
        newR, newC  = row+rc[0], col+rc[1]
        if(newR < 0 or newC < 0 or newR >= N or newC >= N):
            continue
        if(maze[newR][newC] == "." and unvisited_spaces[newR][newC]):
            unvisited_spaces[newR][newC] = False
            moves.append((newR,newC)) 