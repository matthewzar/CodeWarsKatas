from ArraySublistSearch import max_sequence
import pytest


def max_sequence_asserter(numbers, expected):
    assert max_sequence(numbers) == expected


class TestArraySublistSearch:
    def test_empty_list(self):
        max_sequence_asserter([], 0)

    def test_single_positive(self):
        max_sequence_asserter([1], 1)

    def test_single_negative(self):
        max_sequence_asserter([-4], 0)

    def test_two_positive(self):
        max_sequence_asserter([1,2], 3)

    def test_two_negative(self):
        max_sequence_asserter([-1, -9], 0)

    def test_one_positive_one_negative(self):
        max_sequence_asserter([1, -9], 1)

    def test_three_item_with_negative_middle(self):
        max_sequence_asserter([1, -4, 3], 3)

    def test_five_item_with_minor_negative_middle(self):
        numbers = [1, 2, -1, 3, 4]
        max_sequence_asserter(numbers, sum(numbers))

    def test_five_item_with_magor_negative_middle(self):
        numbers = [1, 2, -100, 3, 4]
        max_sequence_asserter(numbers, 3+4)

    def test_five_item_with_magor_negative_middle(self):
        numbers = [7, 4, 11, -11, 39, 36, 10, -6, 37, -10, -32, 44, -26, -34, 43, 43]
        max_sequence_asserter(numbers, 155)
