package snowflake

import (
	"fmt"
	mapset "github.com/deckarep/golang-set"
	"log"
	"testing"
)

func TestSnowflake_Generate(t *testing.T) {
	sf, err := NewSnowflake(10)
	if err != nil {
		log.Fatal(err)
		return
	}
	set := mapset.NewSet()
	for i := 0; i < 1e3; i++ {
		id := sf.Generate()
		if set.Add(id) == false {
			fmt.Println("重复了")
		}
	}

}
