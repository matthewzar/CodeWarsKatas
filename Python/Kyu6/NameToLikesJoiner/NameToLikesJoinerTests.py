from NameToLikesJoiner import likes
import pytest


class TestNameToLikes:
    def test_empty_likes(self):
        assert likes([]) == "no one likes this"

    def test_simple_name(self):
        assert likes(["Peter"]) == "Peter likes this"

    def test_two_names(self):
        assert likes(["Jack", "Jill"]) == "Jack and Jill like this"

    def test_three_names(self):
        assert likes(["A","B","C"]) == "A, B and C like this"

    def test_four_names(self):
        assert likes(["A", "B", "C","D","E"]) == "A, B and 3 others like this"
