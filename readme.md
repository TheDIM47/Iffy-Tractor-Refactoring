http://www.benstopford.com/2011/05/11/the-iffy-tractor-can-they-code-oo/

The Iffy Tractor (Can they code OO?)

A Simple OO Refactoring Problem That Can Be Solved in under One Hour

When interviewing developers I like to use have a variant on the Rover problem 
(posed by Thoughtworks during their interview process). 
The main difference is that the problem is specified as an existing class that 
needs refactoring. (essentially it’s a ‘Replace Conditional with Polymorphism’ refactor) 
and, as such, can be done in around 30-45 mins.

The problem is named “The Iffy Tractor” and you can download the full source for it here: 
TheIffyTractor or take a quick look at the main class here. 
It consists of a single class with a test that moves a tractor around a field. 
The class contains a few large ‘if’ statements that offer a variety of opportunities for the 
candidate to show off their OO skills. In addition the problem of rotating through the 
ordinals N/E/S/W to track direction presents a pleasant problem that has a variety of interesting solutions.

It helps to keep the scope of the problem small. It’s easy to get to grips with and 
start refactoring meaning that you can get something significant out of the candidate 
in under an hour. Its downside is the outcome is affected by weather you’ve used the 
refactor before and you can see that in the candidates approach 
(the test worked very differently in India to in the UK). 
I don’t find it matters that much though. You just have to interpret the results differently.

Like all tests it’s hard to say for certain how accurate or useful they really are. 
However the results from different candidates has been varied so they are at least 
a useful yardstick that provides some empirical measure of the candidate’s ability.

Description
Спроектировать и реализовать прототип framework-а для моделирования игрового мира.

Основной сценарий использования:
•	На двухмерной карте расположены юниты.
•	У каждого юнита есть состояние, характеризующееся набором свойств.
•	Пользователи отправляют команды, на которые юниты реагируют, тем или иным образом меняя своё состояние.
•	Например, у Трактора есть состояние: позиция (координаты на поле) и направление, поддерживает команды: 
повернуться по часовой стрелке (изменить направление), 
двигаться вперёд (в зависимости от текущего направления изменить одну из координат на 1 пункт).
•	В комплекте с фреймворком поставляется некоторый набор юнитов и команд. 
Первоначальный трактор должен органично влиться в этот набор юнитов (отрефакторить код).
•	Требуется предусмотреть механизмы расширения, позволяющие клиентам 
(другим разработчикам, которые используют фреймворк как библиотеку) добавлять поддержку новых юнитов 
и новых команд (не меняя при этом исходный код фреймворка). 
Должна быть реализована возможность переиспользования одних и тех же команд разными юнитами. 
Дизайн фреймворка должен позволять пользователям реализовывать новые юниты, умеющие исполнять как старые, 
так и новые команды. Например, пользовательский юнит Танк мог бы исполнять оригинальные команды 
движения и поворота. При этом старые юниты новым командам обучать не требуется (например, заставлять трактор стрелять не нужно).

В качестве примера предлагается реализовать следующий набор юнитов.

В комплекте с framework-ом:
•	Трактор. Состояние: позиция, направление. Команды: движение вперёд, поворот. (Отрефакторить код изначального Трактора).
•	Камень. Состояние: только позиция. Команды не поддерживаются.
•	Ветер. Состояние: только направление. Команды: поворот.
Юниты, добавляемые пользователем библиотеки:
•	Охранная башня. Состояние: позиция, направление, запас патронов. Команды: поворот, выстрел.
•	Танк. Состояние: позиция, направление, запас патронов. Команды: движение вперёд, поворот, выстрел.

Для демонстрации работоспособности требуется реализовать возможность отправки одной команды 
группе разнородных юнитов. Например, как в компьютерных стратегиях - на карте расположен 
некоторый набор разнообразных юнитов.
 
Пользователь рамкой выделяет группу юнитов и отправляет команду. 
Юниты, которые могут её исполнить - исполняют, остальные - игнорируют. 
Для демонстрации подобного сценария в коде можно объявить коллекцию, заполнить её несколькими 
различными юнитами, затем отправить всем юнитам из этой коллекции какую-либо команду. 
Это можно реализовать в виде юнит-теста или main-метода.
 
Так же в клиентском коде требуется реализовать Макро-команду. 
Частным случаем может быть «ход конем» -  шаг, шаг, поворот, шаг. Но это частный пример.
