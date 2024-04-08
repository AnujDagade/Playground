class State:
    def __init__(self, missionaries, cannibals, boat):
        self.missionaries = missionaries
        self.cannibals = cannibals
        self.boat = boat

    def is_valid(self):
        if self.missionaries < 0 or self.missionaries > 3:
            return False
        if self.cannibals < 0 or self.cannibals > 3:
            return False
        if self.missionaries < self.cannibals and self.missionaries > 0:
            return False
        if 3 - self.missionaries < 3 - self.cannibals and 3 - self.missionaries > 0:
            return False
        return True

    def goal_state(self):
        return self.missionaries == 0 and self.cannibals == 0 and self.boat == 0

    def __eq__(self, other):
        return self.missionaries == other.missionaries and \
               self.cannibals == other.cannibals and \
               self.boat == other.boat

    def __hash__(self):
        return hash((self.missionaries, self.cannibals, self.boat))

    def __str__(self):
        return f"({self.missionaries}, {self.cannibals}, {self.boat})"


def successors(state):
    succ = []
    if state.boat == 1:
        for i in range(3):
            for j in range(3):
                if i + j > 0 and i + j <= 2:
                    succ.append(State(state.missionaries - i, state.cannibals - j, 0))
    else:
        for i in range(3):
            for j in range(3):
                if i + j > 0 and i + j <= 2:
                    succ.append(State(state.missionaries + i, state.cannibals + j, 1))
    return [s for s in succ if s.is_valid()]


def depth_first_search(initial_state):
    visited = set()
    stack = [(initial_state, [])]

    while stack:
        (state, path) = stack.pop()
        if state.goal_state():
            return path + [state]
        if state not in visited:
            visited.add(state)
            for succ in successors(state):
                stack.append((succ, path + [state]))
    return None


def print_solution(solution):
    print("Missionaries and Cannibals Solution:")
    for i, state in enumerate(solution):
        print(f"Step {i + 1}: {state}")


if __name__ == "__main__":
    initial_state = State(3, 3, 1)
    solution = depth_first_search(initial_state)
    if solution:
        print_solution(solution)
    else:
        print("No solution found.")
