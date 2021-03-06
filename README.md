# ticket-price-modeling

## お題

https://cinemacity.co.jp/ticket/

お題としては、この料金表に基づいて映画料金を決定するドメインモデルを作る。

[#チケット料金モデリング のタイムライン](https://twitter.com/search?q=%23%E3%83%81%E3%82%B1%E3%83%83%E3%83%88%E6%96%99%E9%87%91%E3%83%A2%E3%83%87%E3%83%AA%E3%83%B3%E3%82%B0&src=typed_query&f=live)

## このプロジェクトで考慮しないもの

- エムアイカード、駐車場パーク80割引
- 同伴者
- ※3D作品は一律プラス400円。3Dメガネ（Real D）持参の場合は、100円引き。
- ※【極上爆音上映】はレイトショー割適用外です。

## 本質的な部分

### 料金決定の条件をどう宣言的に記述できるか？

複雑な条件をそのままコード化すると、ビジネスルールを明示的な概念として捉えづらいため、仕様パターン(Specification)を採用した。
プラン仕様(PlanSpecification)を使うと、映画のプランを決定するための条件を宣言的に記述できるようになる。PlanSpecificationには顧客仕様CustomerSpecification)と営業日(レイト)仕様(BusinessDaySpecification, LateSpec)などの仕様を内包して、複雑な概念をシンプルに扱えるようにする。

```scala
object Plans {

  val lateSpec = LateSpecification(20)

  object WeekdayNotLatePlans {

    val cinemaCitizenPlan = Plan(
      PlanName.CinemaCitizen,
      Price(1000),
      PlanSpecification(
        customerSpecs = CustomerSpecification.CinemaCitizen,
        businessDayWithLateSpec = Some(BusinessDaySpecification.Weekday and !lateSpec),
        movieDaySpec = None
      )
    )
    // ...  
  }
  // ...
}
```

### 安い料金から検索する

高い金額を提示するのはビジネスの都合上よくないので、安い金額順にプランをソートした上で該当の条件に合うプランを検索する。

```scala
final case class Plans(breachEncapsulationOfValue: Seq[Plan]) {

  def filterByLocalDateTime(localDateTime: LocalDateTime): Plans =
    copy(breachEncapsulationOfValue.filter { plan =>
      val spec = plan.planSpec
      spec.businessDayWithLateSpec
        .fold(false)(_.isSatisfiedBy(localDateTime)) || spec.movieDaySpec.fold(false)(
        _.isSatisfiedBy(localDateTime.toLocalDate)
      )
    })

  def lowestPriceOrder: Plans =
    copy(breachEncapsulationOfValue.sortBy(_.price.amount))

  def findByCustomer(customer: Customer, localDateTime: LocalDateTime): Option[Plan] =
    breachEncapsulationOfValue.find(_.planSpec.isSatisfiedBy(PlanCondition(customer, localDateTime)))
    
}
```

```scala
final case class Order(customer: Customer, orderedAt: LocalDateTime) {

  def plan: Option[Plan] = {
    Plans.all.lowestPriceOrder
      .filterByLocalDateTime(orderedAt)
      .findByCustomer(customer, orderedAt)
  }

}
```


## 他の方の実装コード

- Elm
    - https://github.com/IzumiSy/ticket_price
- Go
    - https://github.com/yamakii/ticket-price-modeling
    - https://github.com/tennashi/ticket-modeling
- Java
    - https://github.com/hiroyuki-endo/ticket-price-modeling
    - https://hackmd.io/@little-hands/tickt-price-modeling
- Kotlin
    - https://github.com/44x1carbon/Ticket-Price-Modeling-Challenge
- Lisp
    - https://github.com/windymelt/ticket-modelling-challenge
- PHP
    - https://github.com/ngmy/ticket-price-modeling
    - https://github.com/choco14t/movie-ticket-fee
- Scala
    - https://github.com/taisukeoe/TicketModellingChallenge
    - https://gist.github.com/akehoyayoi/a65e4b9f2aaac264eceade8622fa89e8
    - https://github.com/deftfitf/TicketFee
    - https://github.com/BambooTuna/cinema-ticket-modelling
- Swift
    - https://github.com/ataka/ccity
- TypeScript
    - https://github.com/okunokentaro/ddd-ticket-price
    - https://github.com/hiroaki-suzuki/ticket-price-modeling-challenge
- Rust
    - https://github.com/ikenox/movie-ticket-domain-modeling
- モデル図
    - https://github.com/tooppoo/ticket-modeling

