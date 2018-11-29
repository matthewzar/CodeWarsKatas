using NUnit.Framework;

using CodeWarsCSharp.Kyu8;

namespace CodeWarsCSharpTests.Kyu8
{
    [TestFixture]
    public class BulletSurvivalTests
    {
        [TestCase(10, 5)]
        [TestCase(100, 40)]
        public void ATrueHero(int bullets, int dragons)
        {
            Assert.IsTrue(BulletSurvival.Hero(bullets, dragons));
        }

        [TestCase(4, 5)]
        [TestCase(1500, 751)]
        [TestCase(0, 1)]
        [TestCase(7, 4)]
        public void AFalseHero(int bullets, int dragons)
        {
            Assert.IsFalse(BulletSurvival.Hero(bullets, dragons));
        }
    }
}